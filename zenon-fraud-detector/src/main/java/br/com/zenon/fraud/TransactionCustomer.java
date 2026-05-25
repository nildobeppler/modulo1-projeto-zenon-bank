package br.com.zenon.fraud;

import java.math.BigDecimal;

public record TransactionCustomer(
        String name,
        BigDecimal oldBalance,
        BigDecimal newBalance
) {
    public TransactionCustomer {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do cliente deve ser preenchido.");
        }
        if (oldBalance == null || oldBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O saldo antigo deve ser maior ou igual a zero.");
        }
        if (newBalance == null || newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O novo saldo atual deve ser maior ou igual a zero.");
        }
    }
}
