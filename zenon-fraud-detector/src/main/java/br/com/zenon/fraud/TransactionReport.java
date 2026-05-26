package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TransactionReport {
    public void load(String fileName) {

        Stats stats = new Stats();

        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            lines.skip(1) // pula header
                    .map(line -> line.split(","))
                    .forEach(filds -> {
                        boolean isFraud = "1".equals(filds[9]);
                        BigDecimal amount = new BigDecimal(filds[2]);
                        stats.addLine(isFraud, amount);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total de linhas: " + stats.getTotalLinhas());
        System.out.println("Total de fraudes: " + stats.getTotalFraudes());
        System.out.println("Valor total transacionado: " + stats.getTotalAmount());
    }
}
