package com.daki.persistence.repository.filtering;

import com.daki.domain.exception.DomainException;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum SpecificationOperation {

    /**
     * @param dhIncio ">=" | 2020-10-01
     */
    GREATER_EQUAL_THAN(Specification.GREATER_EQUAL_THAN.getValue(), (b, v, k) -> predicateGreaterEqualThan(b, v, k)),
    /**
     * @param dhIncio "<=" | 2020-10-01
     */
    LESS_EQUAL_THAN(Specification.LESS_EQUAL_THAN.getValue(), (b, v, k) -> predicateLessEqualThan(b, v, k)),
    /**
     * @param dhIncio "<" | 2020-10-01
     */
    LESS_THAN(Specification.LESS_THAN.getValue(), (b, v, k) -> predicateLessThan(b, v, k)),
    /**
     * @param dhIncio ">" | 2020-10-01
     */
    GREATER_THAN(Specification.GREATER_THAN.getValue(), (b, v, k) -> predicateGreaterThan(b, v, k)),
    /**
     * @param nome "::" | specification
     */
    EQUALS(Specification.EQUALS.getValue(), (b, v, k) -> predicateEquals(b, v, k)),
    /**
     * @param nome ":like:" | specification
     */
    LIKE(Specification.LIKE.getValue(), (b, v, k) -> predicateLike(b, v, k)),
    /**
     * @param nome ":not:" | specification
     */
    NOT_EQUALS(Specification.NOT_EQUALS.getValue(), (b, v, k) -> predicateNotEqual(b, v, k)),
    /**
     * @param nome ":in:" | specification,operation,spring,data
     */
    IN(Specification.IN.getValue(), (b, v, k) -> predicateIN(b, v, k)),
    /**
     * @param nome,telefone,email ":or:" | specification
     */
    OR_LIKES(Specification.OR_LIKES.getValue(), (b, v, k) -> predicatesOrLike(b, v, k));

    private final String operationName;
    private final SpecificationFilter operation;

    SpecificationOperation(String operationName, SpecificationFilter operation) {
        this.operationName = operationName;
        this.operation = operation;
    }

    @FunctionalInterface
    interface SpecificationFilter<T> {
        Predicate predicate(CriteriaBuilder builder, T value, Path... key);
    }

    public <T> Predicate build(CriteriaBuilder builder, Root<?> entity, T value, String key) {
        try {
            final List<Path> paths = EntitySpecificationBuilder.getPathEntity(entity, key);

            final T valueField = EntitySpecificationBuilder.parseFields(value, paths);

            return operation.predicate(builder, valueField, paths.toArray(new Path[paths.size()]));
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public static <T> Predicate predicateLessEqualThan(CriteriaBuilder builder, T value, Path... paths) {
        return builder.lessThanOrEqualTo(paths[0], (Comparable) value);
    }

    private static <T> Predicate predicateGreaterEqualThan(CriteriaBuilder builder, T value, Path... paths) {
        return builder.greaterThanOrEqualTo(paths[0], (Comparable) value);
    }

    private static <T> Predicate predicateLessThan(CriteriaBuilder builder, T value, Path... paths) {
        return builder.lessThan(paths[0], (Comparable) value);
    }

    private static <T> Predicate predicateGreaterThan(CriteriaBuilder builder, T value, Path... paths) {
        return builder.greaterThan(paths[0], (Comparable) value);
    }

    private static <T> Predicate predicateEquals(CriteriaBuilder builder, T value, Path... paths) {
        return builder.equal(paths[0], value);
    }

    private static <T> Predicate predicateLike(CriteriaBuilder builder, T value, Path... paths) {
        return builder.like(builder.lower(paths[0]), builder.literal("%" + value.toString().toLowerCase() + "%"));
    }

    private static <T> Predicate predicateNotEqual(CriteriaBuilder builder, T value, Path... paths) {
        return builder.notEqual(paths[0], value);
    }

    private static <T> Predicate predicateIN(CriteriaBuilder builder, T value, Path... paths) {
        return builder.in(paths[0]).value(value);
    }

    private static <T> Predicate predicatesOrLike(CriteriaBuilder builder, T value, Path... paths) {
        final List<Predicate> predicatesOrLike = Arrays.asList(paths).stream()
                .map(path -> builder.like(builder.lower(path), builder.literal("%" + value.toString().toLowerCase() + "%")))
                .collect(Collectors.toList());

        return builder.or(predicatesOrLike.toArray(new Predicate[predicatesOrLike.size()]));
    }
}
