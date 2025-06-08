package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.repository.CryptoRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class CryptoPortfolioService {

    private final CryptoRepository repository;

    public CryptoPortfolioService(CryptoRepository repository) {
        this.repository = repository;
    }

    public Crypto updateCrypto(int id, Crypto updated) {
        log.info("Updating crypto with id {}", id);
        for (Crypto crypto : repository.findAll()) {
            if (crypto.getId() == id) {
                crypto.setName(updated.getName());
                crypto.setSymbol(updated.getSymbol());
                crypto.setPrice(updated.getPrice());
                crypto.setQuantity(updated.getQuantity());
                return crypto;
            }
        }
        throw new CryptoNotFoundException("Crypto with id " + id + " not found");
    }
    public void addCrypto(Crypto crypto) {
        log.info("Adding crypto: {}", crypto);
        repository.save(crypto);
    }
    public void addCryptos(List<Crypto> cryptos) {
        log.info("Adding cryptos: {}", cryptos);
        repository.saveAll(cryptos);
    }

    public Crypto getCrypto(int id) {
        log.info("Getting crypto with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new CryptoNotFoundException("Crypto with id " + id + " not found"));
    }
    public List<Crypto> getAllCryptos() {
        log.info("Getting all cryptos");
        return repository.findAll();
    }
    public BigDecimal getPortfolioValue() {
        return repository.findAll().stream()
                .map(c -> c.getPrice().multiply(BigDecimal.valueOf(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Crypto> getSortedCryptos(String sortBy) {
        log.info("Getting sorted cryptos by {}", sortBy);
        Sort sort = switch (sortBy.toLowerCase()) {
            case "name"     -> Sort.by("name");
            case "symbol"   -> Sort.by("symbol");
            case "price"    -> Sort.by("price");
            case "quantity" -> Sort.by("quantity");
            default         -> throw new IllegalArgumentException("Unknown sort parameter: " + sortBy);
        };
        return repository.findAll(sort);
    }
}