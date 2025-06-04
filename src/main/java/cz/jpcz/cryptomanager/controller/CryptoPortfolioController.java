package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CryptoPortfolioController {

    private final CryptoPortfolioService cryptoPortfolioService;

    @Autowired
    public CryptoPortfolioController(CryptoPortfolioService cryptoPortfolioService) {
        this.cryptoPortfolioService = cryptoPortfolioService;
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<?> getCrypto(@PathVariable int id) {
        log.info("Getting crypto with id {}", id);
        Crypto crypto = cryptoPortfolioService.getCrypto(id);
        return ResponseEntity.ok(crypto);
    }

    @GetMapping("/cryptos")
    public ResponseEntity<?> getCryptos(@RequestParam(value = "sort", required = false) String sortBy) {
        log.info("Getting all cryptos");
        if (sortBy == null) {
            return ResponseEntity.ok(cryptoPortfolioService.getAllCryptos());
        }
        return ResponseEntity.ok(cryptoPortfolioService.getSortedCryptos(sortBy));
    }

    @PostMapping("/cryptos")
    public ResponseEntity<String> addCrypto(@RequestBody Crypto crypto) {
        log.info("Adding crypto: {}", crypto);
        List<String> missingFields = new ArrayList<>();
        if (crypto.getName() == null) missingFields.add("name");
        if (crypto.getSymbol() == null) missingFields.add("symbol");
        if (crypto.getPrice() == null) missingFields.add("price");
        if (crypto.getQuantity() == null) missingFields.add("quantity");
        if (!missingFields.isEmpty()) {
            String message = "Missing required fields: " + String.join(", ", missingFields);
            log.error(message);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }
        cryptoPortfolioService.addCrypto(crypto);
        log.info("Added new crypto: {}", crypto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Created new crypto");
    }

    @PutMapping("/cryptos/{id}")
    public ResponseEntity<String> updateCrypto(@PathVariable int id, @RequestBody Crypto crypto) {
        log.info("Updating crypto with id {}", id);
        cryptoPortfolioService.updateCrypto(id, crypto);
        return ResponseEntity.ok("Updated crypto with id " + id);
    }

    @GetMapping("/portfolio-value")
    public ResponseEntity<String> getPortfolioValue() {
        log.info("Getting portfolio value");
        return ResponseEntity.ok("Portfolio value: " + cryptoPortfolioService.getPortfolioValue());
    }
}