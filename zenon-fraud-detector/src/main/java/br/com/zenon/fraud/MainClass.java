package br.com.zenon.fraud;

import java.io.IOException;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws IOException {

//        final String FILENAME = "../data/PS_20174392719_1491204439457_log.csv";
        final String FILENAME = "../data/paysim_with_bad_data.csv";

        TransactionIngestor ingester = new TransactionIngestor();
        List<Transaction> transactions = ingester.load(FILENAME);

        transactions.forEach(System.out::println);
    }


}
