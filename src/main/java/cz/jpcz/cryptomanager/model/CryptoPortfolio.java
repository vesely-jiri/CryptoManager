package cz.jpcz.cryptomanager.model;

import cz.jpcz.cryptomanager.test.DataTest;
import cz.jpcz.cryptomanager.util.ConsoleColor;
import cz.jpcz.cryptomanager.util.DebugManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CryptoPortfolio {

    List<Crypto> portfolio = new ArrayList<>();

    public CryptoPortfolio() {
        if (DebugManager.isDebug()) {
            this.portfolio = DataTest.getSampleCryptoPortfolio();
        };
    }
    public CryptoPortfolio(List<Crypto> cryptos) {
        portfolio.addAll(cryptos);
    }

    public CryptoPortfolio(Crypto crypto) {
        portfolio.add(crypto);
    }

    public void add(Crypto crypto) {
        portfolio.add(crypto);
    }

    public void add(List<Crypto> cryptos) {
        portfolio.addAll(cryptos);
    }

    public Crypto get(int id) {
        for (Crypto crypto : portfolio) {
            if (crypto.getId() == id) {
                return crypto;
            }
        } return null;
    }

    public List<Crypto> getAll() {
        return portfolio;
    }

    /**
     * Equivalent to printAll()
     */
    public void listAll() {
        for (Crypto crypto : portfolio) {
            DebugManager.print(ConsoleColor.BLUE + crypto.toString());
        }
    }

    public List<Crypto> getSorted(Comparator<Crypto> comparator) {
        ArrayList<Crypto> sorted = new ArrayList<>(portfolio);
        sorted.sort(comparator);
        return sorted;
    }

    public List<Crypto> getSorted(String sortBy) {
        Comparator<Crypto> comparator = CryptoComparators.getComparator(sortBy);
        return getSorted(comparator);
    }
}