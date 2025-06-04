package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.model.Crypto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CryptoPortfolioService.class)
public class CryptoPortfolioServiceTest {

    private CryptoPortfolioService service = new CryptoPortfolioService();

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
