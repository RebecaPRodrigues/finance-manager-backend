package com.exemplo.webappfinancemanager.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

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
    public static TransactionCategory fromString(String value) {
        return TransactionCategory.valueOf(value.toUpperCase());
    }
}
