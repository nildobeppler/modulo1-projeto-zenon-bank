package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> load(String fileName) throws IOException {

        List<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                try {
                    BigDecimal amount;
                    String[] campos = linha.split(",");
                    int step = Integer.parseInt(campos[0]);
                    TransactionType type = TransactionType.from(campos[1]).orElseThrow(() -> new IllegalArgumentException("Tipo inválido de transação: " + campos[1]));
                    if (campos[2] == null || campos[2].isBlank() || campos[2].isEmpty() || new BigDecimal(campos[2]).compareTo(BigDecimal.ZERO) < 0) {
                        throw new IllegalArgumentException("O valor deve ser maior ou igual a zero.");
                    } else {
                        amount = new BigDecimal(campos[2]);
                    }
                    TransactionCustomer origin = new TransactionCustomer(campos[3], new BigDecimal(campos[4]), new BigDecimal(campos[5]));
                    TransactionCustomer recipient = new TransactionCustomer(campos[6], new BigDecimal(campos[7]), new BigDecimal(campos[8]));
                    boolean isFraud = campos[9].equals("1");
                    boolean isFlaggedFraud = campos[10].equals("1");
                    transactions.add(new Transaction(step, type, amount, origin, recipient, isFraud, isFlaggedFraud));
                } catch (Exception e) {
                    System.err.println("Erro: " + linha + " | " + e);
                }
            }
            return transactions;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler arquivo", e);
        }
    }
}