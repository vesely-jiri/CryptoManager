package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.model.CryptoPortfolio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoPortfolioService {

    private final CryptoPortfolio portfolio;

    public CryptoPortfolioService() {
        this.portfolio = new CryptoPortfolio();
    }

    public Crypto getCrypto(int id) {
        Crypto crypto = portfolio.get(id);
        if (crypto == null) throw new CryptoNotFoundException("Crypto with id " + id + " not found");
        return crypto;
    }

    public void updateCrypto(int id, Crypto crypto) {
        portfolio.update(id, crypto);
    }

    public void addCrypto(Crypto crypto) {
        portfolio.add(crypto);
    }

    public List<Crypto> getAllCryptos() {
        return portfolio.getAll();
    }

    public List<Crypto> getSortedCryptos(String sortBy) {
        return portfolio.getSorted(sortBy);
    }

    public void listAllCryptos() {
        portfolio.listAll();
    }

    public double getPortfolioValue() {
        return portfolio.getPortfolioValue();
    }
}