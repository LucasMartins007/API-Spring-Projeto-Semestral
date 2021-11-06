package com.daki.persistence.repository.filtering;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Specification {

    NOTA("Nota de uso", "Essa api de criação de SQL dinâmica, uso com base nas fields da entidade, " +
                 "essa navegação poder ser feita com o FIELD_SEPARATOR('.') exemplo= (@param pessoa.pessoaFisica.rg :: 123), " +
                 "obrigatório informar qual operação é desejada, assim a SQL sera criada com base no que foi especificado."),

    /**
     * @param dhIncio ">=" | 2020-10-01
     */
    GREATER_EQUAL_THAN(">=", "Maior igual do que, exemplo= (@param dhIncio >= | 2020-10-01)"),
    /**
     * @param dhIncio "<=" | 2020-10-01
     */
    LESS_EQUAL_THAN("<=", "Menor igual que, exemplo= (@param dhIncio <= | 2020-10-01)"),
    /**
     * @param dhIncio "<" | 2020-10-01
     */
    LESS_THAN("<", "Menor que, exemplo= (@param dhIncio < | 2020-10-01)"),
    /**
     * @param dhIncio ">" | 2020-10-01
     */
    GREATER_THAN(">", "Menor que, exemplo= (@param dhIncio > | 2020-10-01)"),
    /**
     * @param nome "::" | specification
     */
    EQUALS("::", "É igual a, exemplo= (@param nome :: | specification)"),
    /**
     * @param nome ":like:" | specification
     */
    LIKE(":like:", "Como, exemplo= (@param nome :like: | specification)"),
    /**
     * @param nome ":not:" | specification
     */
    NOT_EQUALS(":not:", "Não é igual, exemplo= (@param nome :not: | specification)"),
    /**
     * @param nome ":in:" | specification,operation,spring,data
     */
    IN(":in:", "Dentro, exemplo= ( @param nome :in: | specification,operation,spring,data)"),
    /**
     * @param nome,telefone,email ":or:" | specification
     */
    OR_LIKES(":or:", "Ou dentro, exemplo= (@param nome,telefone,email :or: | specification)");

    private final String operationValue, operationDescription;


    Specification(String operationValue, String operationDescription) {
        this.operationValue = operationValue;
        this.operationDescription = operationDescription;
    }

    public String getValue() {
        return this.operationValue;
    }

    public String getDescription() {
        return this.operationDescription;
    }

    private static final List<SpecificationData> ALL = Stream.of(Specification.values())
            .map(s -> new SpecificationData(s.name(), s.operationValue, s.operationDescription))
            .collect(Collectors.toList());

    public static List<SpecificationData> all() {
        return ALL;
    }

    @Data
    static class SpecificationData implements Serializable {
        private final String type, operationValue, operationDescription;
    }
}
