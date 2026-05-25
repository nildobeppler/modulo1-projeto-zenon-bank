package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> load(String fileName) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String linha;
            int count = 0;
            reader.readLine();
            while((linha = reader.readLine()) != null && count < 1000) {
                String[] campos = linha.split(",");
                int step = Integer.parseInt(campos[0]);
                TransactionType type = TransactionType.valueOf(campos[1]);
                BigDecimal amount = new BigDecimal(campos[2]);
                TransactionCustomer origin = new TransactionCustomer(campos[3], new BigDecimal(campos[4]), new BigDecimal(campos[5]));
                TransactionCustomer recipient = new TransactionCustomer(campos[6], new BigDecimal(campos[7]), new BigDecimal(campos[8]));
                boolean isFraud = campos[9].equals("1");
                boolean isFlaggedFraud = campos[10].equals("1");
                transactions.add(new Transaction(step, type, amount, origin, recipient,isFraud,isFlaggedFraud));
                count++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler arquivo", e);
        }
        return transactions;
    }

}
