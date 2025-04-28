package cz.jpcz.cryptomanager.test;

import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.model.CryptoPortfolio;
import cz.jpcz.cryptomanager.util.ConsoleColor;
import cz.jpcz.cryptomanager.util.DebugManager;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    public static void run() {

        DebugManager.setDebug(true);
        DebugManager.print(ConsoleColor.PURPLE + "Running DataTest");

    }

    public static List<Crypto> getSampleCryptoPortfolio() {
        DebugManager.print(ConsoleColor.BLUE + "Creating sample crypto portfolio");
        List<Crypto> portfolio = new ArrayList<>();
        portfolio.add(new Crypto(1, "Bitcoin", "BTC", 1000.0, 1.0));
        portfolio.add(new Crypto(2, "Ethereum", "ETH", 500.0, 1.0));
        portfolio.add(new Crypto(3, "Tether", "USDT", 10000.0, 1.0));
        portfolio.add(new Crypto(4, "Cardano", "ADA", 1.0, 1.0));
        portfolio.add(new Crypto(5, "Solana", "SOL", 10.0, 1.0));
        return portfolio;
    }
}
