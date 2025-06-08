package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.dto.CryptoRequestDTO;
import cz.jpcz.cryptomanager.dto.CryptoResponseDTO;
import cz.jpcz.cryptomanager.mapper.CryptoMapper;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CryptoResponseDTO> getCrypto(@PathVariable int id) {
        log.info("Getting crypto with id {}", id);
        Crypto crypto = cryptoPortfolioService.getCrypto(id);
        CryptoResponseDTO response = CryptoMapper.toResponseDTO(crypto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cryptos")
    public ResponseEntity<List<CryptoResponseDTO>> getCryptos(@RequestParam(value = "sort", required = false) String sortBy) {
        log.info("Getting all cryptos");
        List<CryptoResponseDTO> response;
        if (sortBy == null) {
            response = CryptoMapper.toResponseDTO(cryptoPortfolioService.getAllCryptos());
        } else {
            response = CryptoMapper.toResponseDTO(cryptoPortfolioService.getSortedCryptos(sortBy));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cryptos")
    public ResponseEntity<CryptoResponseDTO> addCrypto(@RequestBody @Valid CryptoRequestDTO request) {
        log.info("Adding crypto: {}", request);
        Crypto created = cryptoPortfolioService.addCrypto(CryptoMapper.toEntity(request));
        log.info("Added new crypto: {}", request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CryptoMapper.toResponseDTO(created));
    }

    @PutMapping("/cryptos/{id}")
    public ResponseEntity<CryptoResponseDTO> updateCrypto(@PathVariable int id,
                                               @RequestBody @Valid CryptoRequestDTO request) {
        log.info("Updating crypto with id {}", id);
        Crypto updated = cryptoPortfolioService.updateCrypto(id, request);
        CryptoResponseDTO response = CryptoMapper.toResponseDTO(updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/portfolio-value")
    public ResponseEntity<String> getPortfolioValue() {
        log.info("Getting portfolio value");
        return ResponseEntity.ok("Portfolio value: " + cryptoPortfolioService.getPortfolioValue());
    }
}