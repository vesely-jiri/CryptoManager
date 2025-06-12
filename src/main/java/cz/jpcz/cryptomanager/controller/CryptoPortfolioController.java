package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.dto.CryptoRequestDTO;
import cz.jpcz.cryptomanager.dto.CryptoResponseDTO;
import cz.jpcz.cryptomanager.mapper.CryptoMapper;
import cz.jpcz.cryptomanager.entity.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CryptoPortfolioController {

    private final CryptoPortfolioService service;

    @Autowired
    public CryptoPortfolioController(CryptoPortfolioService cryptoPortfolioService) {
        this.service = cryptoPortfolioService;
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<CryptoResponseDTO> getCrypto(@PathVariable int id) {
        log.info("Getting crypto with id {}", id);
        Crypto crypto = service.getCrypto(id);
        CryptoResponseDTO response = CryptoMapper.toResponseDTO(crypto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cryptos")
    public ResponseEntity<List<CryptoResponseDTO>> getCryptos(
            @RequestParam(value = "sort", required = false) String sortBy) {
        log.info("Getting all cryptos{}", sortBy != null ? " sorted by " + sortBy : "");
        List<Crypto> cryptos = sortBy == null
                ? service.getAllCryptos()
                : service.getSortedCryptos(sortBy);
        List<CryptoResponseDTO> response = CryptoMapper.toResponseDTO(cryptos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cryptos")
    public ResponseEntity<CryptoResponseDTO> addCrypto(@RequestBody @Valid CryptoRequestDTO request) {
        log.info("Adding crypto: {}", request);
        Crypto created = service.addCrypto(CryptoMapper.toEntity(request));
        log.info("Added new crypto: {}", request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CryptoMapper.toResponseDTO(created));
    }

    @PutMapping("/cryptos/{id}")
    public ResponseEntity<CryptoResponseDTO> updateCrypto(@PathVariable int id,
                                               @RequestBody @Valid CryptoRequestDTO request) {
        log.info("Updating crypto with id {}", id);
        Crypto updated = service.updateCrypto(id, request);
        CryptoResponseDTO response = CryptoMapper.toResponseDTO(updated);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/portfolio-value")
    public ResponseEntity<String> getPortfolioValue() {
        log.info("Getting portfolio value");
        return ResponseEntity.ok("Portfolio value: " + service.getPortfolioValue());
    }
}