package br.com.zenon.fraud;

import java.math.BigDecimal;

public class Stats {

    private long totalLinhas;
    private long totalFraudes;
    private BigDecimal totalAmount;

    public Stats() {
        this.totalLinhas = 0;
        this.totalFraudes = 0;
        this.totalAmount = BigDecimal.ZERO;
    }

    public void addLine(boolean isFraud, BigDecimal amount) {
        this.totalLinhas++;
        if (isFraud) {
            this.totalFraudes++;
        }
        this.totalAmount = this.totalAmount.add(amount);
    }

    public long getTotalLinhas() {
        return totalLinhas;
    }

    public long getTotalFraudes() {
        return totalFraudes;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
