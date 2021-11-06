/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daki.domain.service;


import com.daki.api.converter.Converter;
import com.daki.domain.exception.DomainException;
import com.daki.domain.context.IContext;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.model.AbstractEntity;
import com.daki.domain.model.dto.system.AbstractDto;
import com.daki.domain.patterns.OperationsQueryParam;
import com.daki.domain.util.ClassUtil;
import com.daki.domain.util.ListUtil;
import com.daki.domain.util.RequestUtil;
import com.daki.persistence.repository.filtering.EntitySpecificationBuilder;
import com.daki.persistence.repository.filtering.FindAllSpecification;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.util.ClassUtils;


/**
 *
 * @author lucas
 * @param <I> Identificador da chave primária do repositorio Integer, Long
 * @param <E> Classe de entidade
 *
 */
public abstract class AbstractServiceImpl<E extends AbstractEntity, I extends Serializable, DTO extends AbstractDto>  implements IAbstractService<E, I, DTO>{

    private final Class<E> entityClass;
    private final Class<DTO> dtoClass;
    
     public AbstractServiceImpl() {
        Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        entityClass = (Class<E>) actualTypeArguments[0];
         dtoClass = (Class<DTO>) actualTypeArguments[2];
    }

    
    private IContext getContext() {
        return IContext.context();
    }
    
    public JpaRepository<E, I> getRepository() {
        return getContext().getRepositoryFromClass(entityClass);
    }

    public <R extends Repository> R getRepository(Class<R> classRespository) {
        return getContext().getBean(classRespository);
    }

    public <R> R getService(Class<R> classService) {
        return getContext().getBean(classService);
    }
    
    public E findAndValidate(I id) {
        if (id == null) {
            throw new DomainException("Mensagem de nullpointer");
        }
        return getRepository()
                .findById(id)
                .orElseThrow(() -> new DomainException("Entidade não encontrada", entityClass.getSimpleName(), id));
    }

    @Override
    public DTO findById(I id) {
        final E entidade = findAndValidate(id);

        return converterEntityToDTO(entidade);
    }

    @Override
    public List<DTO> findAll() {
        final List<E> entidades = getRepository().findAll();

        return entidades.stream()
                .map(this::converterEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DTO> findAll(Pageable pageable) {
        Sort sort = pageable.getSort();

        if (sort == null) {
            sort = sortBy();
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }

        final Page<E> entidades = getRepository().findAll(pageable);

        return entidades.map(this::converterEntityToDTO);
    }

    private Sort sortBy() {
        return Sort.unsorted();
    }

    @Override
    public Page<DTO> findAll(Pageable pageable, Map filters, ExampleMatcher.StringMatcher matchFilter) {
        final JpaRepository<E, I> repository = getRepository();

        Page<E> entidades;

        Sort sort = pageable.getSort();

        if (sort == null) {
            sort = RequestUtil.resolveSort((String) filters.get("sort"));

            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }

        if (!validarFilters(filters)) {
            final List filtersSearch = EntitySpecificationBuilder.parseFilterSpecification(filters);

            if (ListUtil.isNotNullOrEmpty(filtersSearch)) {
                if (JpaSpecificationExecutor.class.isAssignableFrom(repository.getClass())) {
                    entidades = FindAllSpecification
                            .useRepository((JpaSpecificationExecutor) repository)
                            .filterBy(filters)
                            .findAllPageable(pageable);
                } else {
                    throw new DomainException(EnumDomainException.REPOSITORY_IMPLEMENTS_NOT_FOUND.getMessage(), entityClass.getSimpleName(), JpaSpecificationExecutor.class.getSimpleName());
                }

            } else {
                entidades = repository.findAll(getQueryExampleEntity(filters, matchFilter), pageable);
            }

        } else {
            entidades = repository.findAll(pageable);
        }

        return entidades.map(this::converterEntityToDTO);
    }


    @Override
    public Page<DTO> findAll(Pageable pageable, Map filters) {
        final JpaRepository<E, I> repository = getRepository();

        Page<E> entidades = null;

        if (pageable.getSort() == null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), RequestUtil.resolveSort((String) filters.get("sort")));
        }

        if (!validarFilters(filters)) {
            if (!JpaSpecificationExecutor.class.isAssignableFrom(repository.getClass())) {
                throw new DomainException(EnumDomainException.REPOSITORY_IMPLEMENTS_NOT_FOUND.getMessage(),
                        entityClass.getSimpleName(),
                        JpaSpecificationExecutor.class.getSimpleName());
            }

            entidades = FindAllSpecification
                    .useRepository((JpaSpecificationExecutor) repository)
                    .filterBy(filters)
                    .findAllPageable(pageable);
        }

        if (entidades == null || entidades.isEmpty()) {
            entidades = repository.findAll(pageable);
        }

        return entidades.map(this::converterEntityToDTO);
    }

    private E converterDTOToEntity(DTO dto) {
        return Converter.converterDTOParaEntity(dto, entityClass);
    }

    protected DTO converterEntityToDTO(E entidade) {
        return Converter.converterEntityParaDTO(entidade, dtoClass);
    }
    private boolean validarFilters(Map<String, Object> filters) {
        OperationsQueryParam.OPERATIONS.stream()
                .filter(filters::containsKey)
                .forEach(filters::remove);
        return (filters != null && filters.isEmpty());
    }

    @Deprecated
    private Example<E> getQueryExampleEntity(Map<String, Object> filtersQueryParam, ExampleMatcher.StringMatcher matcherFilter) {
        try {
            ExampleMatcher exampleMatcher;
            if (matcherFilter != null) {
                exampleMatcher = ExampleMatcher.matching()
                        .withStringMatcher(matcherFilter)
                        .withIgnoreCase();
            } else {
                exampleMatcher = ExampleMatcher.matching()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreCase();
            }

            final E instanceEntity = filtersEntity(filtersQueryParam);

            return Example.of(instanceEntity, exampleMatcher);
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Deprecated
    private E filtersEntity(Map<String, Object> filters) {
        try {
            final Map<String, Object> toMapFilters = new HashMap();

            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                if (filter.getKey() != null || filter.getValue() != null) {
                    final String key = filter.getKey().trim();
                    Object value = filter.getValue();

                    if (key.contains(".")) {
                        final List<String> filtersEntry = Arrays.asList(key.split("\\."));

                        if (filtersEntry != null && !filtersEntry.isEmpty()) {
                            Field field = filtersEntry.stream()
                                    .flatMap(fe -> Stream.of(entityClass.getDeclaredFields()).filter(f -> f.getName().equalsIgnoreCase(fe)))
                                    .filter(fe -> !ClassUtils.isPrimitiveOrWrapper(fe.getType()))
                                    .findFirst()
                                    .orElse(null);

                            if (field != null) {
                                Class classField;

                                List beanSpectList = null;
                                if (Collection.class.isAssignableFrom(field.getType())) {
                                    classField = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

                                    beanSpectList = new ArrayList();
                                } else {
                                    classField = field.getType();
                                }

                                if (classField != null) {
                                    final boolean existsKey = toMapFilters.entrySet().stream()
                                            .anyMatch(m -> m.getKey().equalsIgnoreCase(field.getName()));

                                    Object beanSpect;

                                    if (!existsKey) {
                                        beanSpect = BeanUtils.instantiateClass(classField);
                                    } else {
                                        beanSpect = toMapFilters.get(field.getName());
                                    }

                                    Field fieldBean = null;

                                    if (beanSpect != null) {
                                        fieldBean = filtersEntry.stream()
                                                .filter(f -> !f.equalsIgnoreCase(field.getName()))
                                                .flatMap(f -> Stream.of(classField.getDeclaredFields()).filter(fb -> fb.getName().equalsIgnoreCase(f)))
                                                .findFirst()
                                                .orElse(null);
                                    }

                                    if (!existsKey && fieldBean != null) {
                                        value = parseValue(value, fieldBean);

                                        BeanUtilsBean.getInstance().setProperty(beanSpect, fieldBean.getName(), value);

                                        if (beanSpectList != null) {
                                            beanSpectList.add(beanSpect);

                                            beanSpect = beanSpectList;
                                        }

                                        toMapFilters.put(field.getName(), beanSpect);
                                    }

                                    if (existsKey && fieldBean != null) {
                                        if (beanSpectList != null) {
                                            beanSpect = ((Collection) beanSpect).stream().findFirst().get();
                                        }

                                        value = parseValue(value, fieldBean);

                                        BeanUtilsBean.getInstance().setProperty(beanSpect, fieldBean.getName(), value);
                                    }
                                }
                            }
                        }
                    } else {
                        final Field field = Stream.of(entityClass.getDeclaredFields())
                                .filter(f -> f.getName().equalsIgnoreCase(key))
                                .findFirst()
                                .orElse(null);

                        value = parseValue(value, field);

                        toMapFilters.put(key, value);
                    }
                }
            }

            final E instantiateClass = BeanUtils.instantiateClass(this.entityClass);

            BeanUtilsBean.getInstance().populate(instantiateClass, toMapFilters);

            return instantiateClass;
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Deprecated
    private Object parseValue(Object value, Field field) {
        if (field != null) {
            if (field.getType().isEnum()) {
                return Enum.valueOf((Class<Enum>) field.getType(), value.toString());
            }

            return ClassUtil.cast(field.getType(), value);
        }
        return value;
    }
    
}
