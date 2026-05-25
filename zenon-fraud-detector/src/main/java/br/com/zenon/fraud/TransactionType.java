package br.com.zenon.fraud;

import java.util.Arrays;
import java.util.Optional;

public enum TransactionType {
    CASH_IN, CASH_OUT, DEBIT, PAYMENT, TRANSFER;

    public static Optional<TransactionType> from(String value) {
        return Arrays.stream(values())
                .filter(t -> t.name().equals(value))
                .findFirst();
    }

}
