package br.com.zenon.fraud;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AnaliseDadosStream {

    public void load(String fileName) throws IOException {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.of("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        TransactionIngestor ingester = new TransactionIngestor();
        List<Transaction> transactions = ingester.load(fileName);
        FraudAnalyzer analyzer = new FraudAnalyzer(transactions);

        IO.println("1. Total de Fraudes: " + analyzer.countFrauds());

        IO.println("2. Top 3 Fraudes de Maior Valor:");
        analyzer.top3Frauds().forEach(amount -> IO.println(formatter.format(amount)));

        IO.println("3. Clientes Suspeitos: " + analyzer.top5Suspects());

        IO.println("4. Prejuízo Total: " + formatter.format(analyzer.totalLoss()));

        IO.println("5. Fraudes por Tipo:");
        analyzer.countFraudsByType().forEach((type, count) -> IO.println(type + ": " + count));
    }
}
