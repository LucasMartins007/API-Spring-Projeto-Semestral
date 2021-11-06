package com.daki.persistence.repository.filtering;

import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;
import com.daki.domain.util.ClassUtil;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.util.FieldUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySpecificationBuilder {

    public static Optional<Specification> parse(List<String> filters) {
        if (filters == null || filters.isEmpty()) {
            return Optional.empty();
        }

        List<Specification> criterias = mapSpecifications(filters);
        if (criterias.isEmpty()) {
            return Optional.empty();
        }

        Specification root = Specification.where(criterias.get(0));
        for (int index = 1; index < criterias.size(); index++) {
            root = Specification.where(root).and(criterias.get(index));
        }
        return Optional.of(root);
    }

    private static List<Specification> mapSpecifications(final List<String> filters) {
        return filters.stream().map(filter -> {
            final SpecificationOperation op = operation(filter);

            if (op != null) {
                final int index = filter.indexOf(op.getOperationName());

                if (index > 0) {
                    final String key = filter.substring(0, index);
                    final String value = filter.substring(index + op.getOperationName().length());
                    return (Specification) (root, query, cb) -> op.build(cb, root, value, key);
                }
            }
            return null;
        }).filter(s -> s != null).collect(Collectors.toList());
    }


    public static SpecificationOperation operation(final String searchFilter) {
        for (SpecificationOperation op : SpecificationOperation.values()) {
            int index = searchFilter.indexOf(op.getOperationName());
            if (index > 0) {
                return op;
            }
        }
        return null;
    }

    static List<Path> getPathEntity(Root<?> rootEntity, String attributeName) {
        try {
            List<Path> paths = new ArrayList();

            if (attributeName != null && attributeName.contains(".") || attributeName.contains(",")) {
                if (attributeName.contains(".")) {
                    final Path pathChildren = Stream.of(attributeName.split("\\."))
                            .reduce((Path<?>) rootEntity, (root, path) -> {
                                final Field field = DomainException.checked(() -> FieldUtils.getField(root.getJavaType(), path));

                                if (field != null &&
                                        field.getType().isAnnotationPresent(Entity.class) ||
                                        field.getType().isAnnotationPresent(Table.class) ||
                                        Iterable.class.isAssignableFrom(field.getType())) {
                                    return ((From<?, ?>) root).join(path);
                                }

                                return root.get(path);
                            }, (previus, next) -> next);

                    paths.add(pathChildren);
                }

                if (attributeName.contains(",")) {
                    Stream.of(attributeName.split(","))
                            .map(rootEntity::get)
                            .forEach(e -> paths.add(e));
                }
            } else {

                paths.add(rootEntity.get(attributeName));
            }

            return paths;
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public static List<String> parseFilterSpecification(Map<String, Object> toMapQueryParam) {
        return toMapQueryParam.entrySet().stream()
                .map(x -> (x.getKey() + x.getValue()))
                .filter(x -> operation(x) != null)
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }

    static <T> T parseFields(T fields, List<Path> pathsFieldEntity) {
        if (fields instanceof String && ((String) fields).contains(",")) {
            return parseField((List<T>) Arrays.asList(((String) fields).split(",")), pathsFieldEntity);
        }
        return parseField(fields, pathsFieldEntity);
    }

    private static <T> T parseField(List<T> fieldValue, List<Path> pathsFieldEntity) {
        return (T) fieldValue.stream()
                .map(x -> parseField(x, pathsFieldEntity))
                .collect(Collectors.toList());
    }

    private static <T> T parseField(T fieldValue, List<Path> pathsFieldEntity) {
        try {
            final Path<T> pathEntity = pathsFieldEntity.get(0);

            T value = fieldValue;

            if (pathEntity != null) {
                final Class<? extends T> javaType = pathEntity.getJavaType();

                if (javaType != null) {
                    if (javaType.isEnum()) {
                        final Enum existsEnum = Enum.valueOf((Class<Enum>) pathEntity.getJavaType(), fieldValue.toString());

                        if (existsEnum != null) {
                            value = (T) existsEnum;
                        }

                    } else {
                        final T valueField = ClassUtil.cast(pathEntity.getJavaType(), fieldValue);

                        if (valueField != null) {
                            value = valueField;
                        }
                    }

                }
            }

            final Class<?> classValue = value.getClass();

            for (Path path : pathsFieldEntity) {
                if (!path.getJavaType().equals(classValue)) {
                    final SingularAttributePath singularAttributePath = (SingularAttributePath) path;
                    if (singularAttributePath != null) {
                        final SingularAttribute attribute = singularAttributePath.getAttribute();
                        if (attribute != null) {
                            final String nameTypeClass = (attribute.getName() + "=" + path.getJavaType().getSimpleName());

                            throw new DomainException(EnumDomainException.ENTITY_TYPE_INCOMPATIBLE.getMessage(), nameTypeClass, classValue.getSimpleName());
                        }
                    }
                }
            }

            return value;

        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }
}
