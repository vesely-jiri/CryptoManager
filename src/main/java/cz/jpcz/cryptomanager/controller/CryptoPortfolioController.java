package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.exception.UnknownSortParameterException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CryptoPortfolioController {

    private final CryptoPortfolioService cryptoPortfolioService;

    @Autowired
    public CryptoPortfolioController(CryptoPortfolioService cryptoPortfolioService) {
        this.cryptoPortfolioService = cryptoPortfolioService;
    }

    @PostMapping("/cryptos")
    public ResponseEntity<String> addCrypto(@RequestBody Crypto crypto) {
        List<String> missingFields = new ArrayList<>();
        if (crypto.getName() == null) missingFields.add("name");
        if (crypto.getSymbol() == null) missingFields.add("symbol");
        if (crypto.getPrice() == null) missingFields.add("price");
        if (crypto.getQuantity() == null) missingFields.add("quantity");
        if (!missingFields.isEmpty()) {
            String message = "Missing required fields: " + String.join(", ", missingFields);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }
        cryptoPortfolioService.addCrypto(crypto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Created new crypto");
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<?> getCrypto(@PathVariable int id) {
        try {
            Crypto crypto = cryptoPortfolioService.getCrypto(id);
            return ResponseEntity.ok(crypto);
        } catch (CryptoNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/cryptos")
    public ResponseEntity<?> getCryptos(@RequestParam(value = "sort", required = false) String sortBy) {
        if (sortBy == null) {
            return ResponseEntity.ok(cryptoPortfolioService.getAllCryptos());
        }
        try {
            return ResponseEntity.ok(cryptoPortfolioService.getSortedCryptos(sortBy));
        } catch (UnknownSortParameterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}