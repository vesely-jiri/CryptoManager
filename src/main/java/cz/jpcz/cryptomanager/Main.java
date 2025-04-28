package cz.jpcz.cryptomanager;

import cz.jpcz.cryptomanager.test.DataTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Executing program in second portfolio project: CryptoManager");

        DataTest.run();

        SpringApplication.run(Main.class, args);
    }
}