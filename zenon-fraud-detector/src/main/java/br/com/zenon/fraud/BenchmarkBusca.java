package br.com.zenon.fraud;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BenchmarkBusca {
    public void load(String fileName) throws IOException {
        List<Transaction> transactions = new TransactionIngestor().load(fileName);

        TransactionRepository listRepo = new TransactionListRepository(transactions);
        TransactionRepository mapRepo = new TransactionMapRepository(transactions);

        String target = "C1868032458"; // pior caso

// LIST
        long startList = System.nanoTime();
        Optional<Transaction> resultList = listRepo.findByOriginName(target);
        long endList = System.nanoTime();

        IO.println("LIST tempo: " + (endList - startList) + " ns");

// MAP
        long startMap = System.nanoTime();
        Optional<Transaction> resultMap = mapRepo.findByOriginName(target);
        long endMap = System.nanoTime();

        IO.println("MAP tempo: " + (endMap - startMap) + " ns");

        IO.println("--------------------");

        String existing = "C1231006815";
        String nonExisting = "C12345";

        listRepo.findByOriginName(nonExisting).ifPresentOrElse(
                IO::println,
                () -> IO.println("Transação não encontrada para o cliente: " + nonExisting)
        );

        listRepo.findByOriginName(existing).ifPresent(IO::println);
    }
}
