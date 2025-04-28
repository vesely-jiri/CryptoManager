package cz.jpcz.cryptomanager;

import cz.jpcz.cryptomanager.test.DataTest;
import cz.jpcz.cryptomanager.util.DebugManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DebugManager.setDebug(true);

        SpringApplication.run(Main.class, args);

        DataTest.run();
    }
}