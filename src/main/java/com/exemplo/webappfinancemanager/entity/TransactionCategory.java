package com.exemplo.webappfinancemanager.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionCategory {
    SALARIO,
    ALIMENTACAO,
    MORADIA,
    LAZER,
    TRANSPORTE,
    EDUCACAO,
    SAUDE,
    OUTROS;

    @JsonCreator
    public static TransactionCategory fromValue(String value) {
        try {
            return TransactionCategory.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Categoria inválida: " + value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
