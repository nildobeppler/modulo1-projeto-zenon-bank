package br.com.zenon.fraud;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        TransactionIngestor ingester = new TransactionIngestor();
        List<Transaction> transactions = ingester.load("../data/PS_20174392719_1491204439457_log.csv");

        transactions.stream()
                .limit(10)
                .forEach(System.out::println);
    }


}
