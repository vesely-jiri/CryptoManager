package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.exception.UnknownSortParameterException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoPortfolioController {

    private final CryptoPortfolioService cryptoPortfolioService;

    @Autowired
    public CryptoPortfolioController(CryptoPortfolioService cryptoPortfolioService) {
        this.cryptoPortfolioService = cryptoPortfolioService;
    }

    @PostMapping("/cryptos")
    public String addCrypto(@RequestBody Crypto crypto) {
        cryptoPortfolioService.addCrypto(crypto);
        return "Adding crypto";
    }

    @GetMapping("/cryptos/{id}")
    public String getCrypto(@PathVariable int id) {
        try {
            return cryptoPortfolioService.getCrypto(id).toString();
        } catch (CryptoNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/cryptos")
    public List<Crypto> getCryptos(@RequestParam(value = "sort", required = false) String sortBy) {
        if (sortBy == null) {
            return cryptoPortfolioService.getAllCryptos();
        }
        try {
            return cryptoPortfolioService.getSortedCryptos(sortBy);
        } catch (UnknownSortParameterException e) {
            return cryptoPortfolioService.getAllCryptos();
        }
    }
}