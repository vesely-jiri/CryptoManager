package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.model.Crypto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoPortfolioServiceTest {

    private CryptoPortfolioService service;

    @BeforeEach
    void setUp() {
        service = new CryptoPortfolioService();
    }
    @DisplayName("Should return portfolio value of all cryptos in portfolio using service.getPortfolioValue()")
    @Test
    void shouldReturnPortfolioValue() {
        List<Crypto> cryptos = List.of(
                new Crypto(1, "Bitcoin", "BTC", 1000.0, 10.0), //10000
                new Crypto(2, "Ethereum", "ETH", 2000.0, 5.0), //10000
                new Crypto(3, "Ripple", "XRP", 4000.0, 20.0) //80000
        );
        service.addCryptos(cryptos);

        assertEquals(100000.0, service.getPortfolioValue());
    }

    @DisplayName("Should return sorted cryptos using service.getSortedCryptos(\"price\")")
    @Test
    void shouldReturnSortedCryptos() {
        List<Crypto> cryptos = List.of(
                new Crypto(2, "Ethereum", "ETH", 1000.0, 14.0), //14000
                new Crypto(3, "Ripple", "XRP", 4000.0, 20.0), //80000
                new Crypto(1, "Bitcoin", "BTC", 300.0, 20.0) //6000
        );
        service.addCryptos(cryptos);
        List<Crypto> sorted = service.getSortedCryptos("price");

        assertEquals("Bitcoin", sorted.get(0).getName());
        assertEquals("Ethereum", sorted.get(1).getName());
        assertEquals("Ripple", sorted.get(2).getName());
    }
}
