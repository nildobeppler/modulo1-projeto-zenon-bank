package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long countFrauds() {
        return transactions.stream().filter(Transaction::isFraud).count();
    }

    public List<BigDecimal> top3Frauds() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
    }

    public Set<String> top5Suspects() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted((t1, t2) -> t2.amount().compareTo(t1.amount()))
                .map(t -> t.origin().name())
                .distinct()
                .limit(5)
                .collect(Collectors.toSet());
    }

    public BigDecimal totalLoss() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> countFraudsByType() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.counting()
                ));
    }

    /*
    ✔️ filter → selecionar fraudes
    ✔️ map → extrair dados
    ✔️ sorted → ordenar
    ✔️ limit → top N
    ✔️ distinct → remover duplicados
    ✔️ reduce → soma
    ✔️ groupingBy → agregação
     */

    /*
    1. Apenas transações onde isFraud == true, imprima o tamanho da lista.
    2. Imprima as 3 fraudes de maior valor (amount).
    3. Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.
    4. Calcule o prejuízo total causado pelas fraudes (soma dos amount).
    5. Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...).
    */
}
