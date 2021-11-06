package com.daki.persistence.repository.filtering;

import com.daki.domain.exception.DomainException;
import com.daki.domain.util.RequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FindAllSpecification<E, R extends JpaSpecificationExecutor<E>> {

    private final R repository;

    private Specification<E> filters;

    private Sort sort = Sort.unsorted();

    public static <E, R extends JpaSpecificationExecutor<E>> FindAllSpecification<E, R> useRepository(R repository) {
        return new FindAllSpecification(repository);
    }

    public static Optional<Specification> useSpecification(Map mapOfQueryParamRequest) {
        final List queryParamRequestToList = Optional.ofNullable(EntitySpecificationBuilder.parseFilterSpecification(mapOfQueryParamRequest))
                .orElseThrow(() -> new DomainException("Not found specification."));

        final Optional<Specification> opFilters = EntitySpecificationBuilder.parse(queryParamRequestToList);

        return Optional.ofNullable(Specification.where(opFilters.get()));
    }

    private FindAllSpecification(R repository) {
        this.repository = repository;
    }

    public List<E> findAll(int page, int limit) {
        try {
            return new LinkedList<E>(repository.findAll(filters, PageRequest.of(page, limit, sort)).getContent());
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public Page<E> findAllPageable(Pageable pageable) {
        try {
            return repository.findAll(filters, pageable);
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public FindAllSpecification<E, R> filterBy(final Map mapOfQueryParamRequest) {
        final List listOfQueryParamRequest = EntitySpecificationBuilder.parseFilterSpecification(mapOfQueryParamRequest);

        Optional<Specification> opFilters = EntitySpecificationBuilder.parse(listOfQueryParamRequest);
        if (opFilters.isPresent()) {
            if (filters == null) {
                filters = Specification.where(opFilters.get());
            } else {
                filters = filters.and(opFilters.get());
            }
        }

        return this;
    }

    public FindAllSpecification<E, R> filterBy(final HttpServletRequest request) {
        return filterBy(RequestUtil.extractQueryParamVariableToMap(request));
    }

    public FindAllSpecification<E, R> sortBy(String orderBy, String orderDir) {
        if (StringUtils.hasLength(orderBy)) {
            sort = Sort.by(Sort.Direction.fromOptionalString(orderDir).orElse(Sort.Direction.ASC), orderBy);
        }

        return this;
    }
}