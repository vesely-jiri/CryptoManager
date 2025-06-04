package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.model.CryptoPortfolio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CryptoPortfolioService {
    private final CryptoPortfolio portfolio;

    public CryptoPortfolioService() {
        this.portfolio = new CryptoPortfolio();
    }

    public Crypto updateCrypto(int id, Crypto updated) {
        log.info("Updating crypto with id {}", id);
        for (Crypto c : portfolio.getCryptos()) {
            if (c.getId() == id) {
                c.setName(updated.getName());
                c.setSymbol(updated.getSymbol());
                c.setPrice(updated.getPrice());
                c.setQuantity(updated.getQuantity());
                return c;
            }
        }
        throw new CryptoNotFoundException("Crypto with id " + id + " not found");
    }
    public void addCrypto(Crypto crypto) {
        log.info("Adding crypto: {}", crypto);
        portfolio.add(crypto);
    }
    public void addCryptos(List<Crypto> cryptos) {
        log.info("Adding cryptos: {}", cryptos);
        portfolio.add(cryptos);
    }

    public Crypto getCrypto(int id) {
        log.info("Getting crypto with id {}", id);
        Crypto crypto = portfolio.getCrypto(id);
        if (crypto == null) throw new CryptoNotFoundException("Crypto with id " + id + " not found");
        return crypto;
    }
    public List<Crypto> getAllCryptos() {
        log.info("Getting all cryptos");
        return portfolio.getCryptos();
    }
    public double getPortfolioValue() {
        log.info("Getting portfolio value {}", portfolio.getPortfolioValue());
        return portfolio.getPortfolioValue();
    }
    public List<Crypto> getSortedCryptos(String sortBy) {
        log.info("Getting sorted cryptos by {}", sortBy);
        return portfolio.getSorted(sortBy);
    }
}