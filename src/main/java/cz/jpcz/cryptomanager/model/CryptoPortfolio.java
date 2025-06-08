package cz.jpcz.cryptomanager.model;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CryptoPortfolio {
    private List<Crypto> cryptos = new ArrayList<>();

    public CryptoPortfolio() {}
    public CryptoPortfolio(List<Crypto> newCryptos) {
        cryptos.addAll(newCryptos);
    }
    public CryptoPortfolio(Crypto crypto) {
        cryptos.add(crypto);
    }

    public void add(Crypto crypto) {
        cryptos.add(crypto);
    }
    public void add(List<Crypto> newCryptos) {
        cryptos.addAll(newCryptos);
    }
    public void remove(int id) {
        for (Crypto crypto : cryptos) {
            if (crypto.getId() == id) {
                cryptos.remove(crypto);
                return;
            }
        }
        throw new CryptoNotFoundException("Crypto with id " + id + " not found");
    }

    public List<Crypto> getCryptos() {
        return new ArrayList<>(cryptos);
    }
    public Crypto getCrypto(int id) {
        for (Crypto crypto : cryptos) {
            if (crypto.getId() == id) {
                return crypto;
            }
        } return null;
    }

    public List<Crypto> getSorted(Comparator<Crypto> comparator) {
        ArrayList<Crypto> sorted = new ArrayList<>(cryptos);
        sorted.sort(comparator);
        return sorted;
    }
}