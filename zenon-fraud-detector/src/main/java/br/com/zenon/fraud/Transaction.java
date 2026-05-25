package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        TransactionCustomer origin,
        TransactionCustomer recipient,
        boolean isFraud,
        boolean isFlaggedFraud
) {
    public Transaction {
        if (step < 0) {
            throw new IllegalArgumentException("O passo da transação deve ser maior que zero -> Step: " + step);
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor deve ser positivo -> Amount: " + amount);
        }
        if (type == null) {
            throw new IllegalArgumentException("O tipo da transação não pode ser nulo -> Type: " + type);
        }
        if (origin == null || recipient == null) {
            throw new IllegalArgumentException("Os clientes não devem ser nulos.");
        }
    }
}