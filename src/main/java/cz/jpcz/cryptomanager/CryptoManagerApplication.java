package cz.jpcz.cryptomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoManagerApplication {
    public static void main(String[] args) {
        System.out.println("Executing program in second portfolio project: CryptoManager");

        SpringApplication.run(CryptoManagerApplication.class, args);
    }
}