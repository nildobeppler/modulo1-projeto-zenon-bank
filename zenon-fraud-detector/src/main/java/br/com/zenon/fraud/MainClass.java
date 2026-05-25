package br.com.zenon.fraud;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainClass {

    public static void main(String[] args) throws IOException {

        final String FILENAME = "../data/PS_20174392719_1491204439457_log.csv";
//        final String FILENAME = "../data/paysim_with_bad_data.csv";

//        new AnaliseDadosStream().load(FILENAME);
        new BenchmarkBusca().load(FILENAME);
    }


}
