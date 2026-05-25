package br.com.zenon.fraud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactions = new HashMap<>();

    public TransactionMapRepository(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            this.transactions.put(transaction.origin().name(), transaction);
        }
    }

    @Override
    public Optional<Transaction> findByOriginName(String name) {
        return transactions.containsKey(name) ? Optional.of(transactions.get(name)) : Optional.empty();
    }
}
