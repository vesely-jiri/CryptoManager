package cz.jpcz.cryptomanager.service;

import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.repository.CryptoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CryptoPortfolioServiceTest {

    @Mock
    private CryptoRepository repository;

    @InjectMocks
    private CryptoPortfolioService service;

    @DisplayName("Should return portfolio value of all cryptos in portfolio using service.getPortfolioValue()")
    @Test
    void shouldReturnPortfolioValue() {
        List<Crypto> cryptos = List.of(
                new Crypto(1, "Bitcoin", "BTC", BigDecimal.valueOf(1000.0), 10.0), //10000
                new Crypto(2, "Ethereum", "ETH", BigDecimal.valueOf(2000.0), 5.0), //10000
                new Crypto(3, "Ripple", "XRP", BigDecimal.valueOf(4000.0), 20.0) //80000
        );
        Mockito.when(repository.findAll()).thenReturn(cryptos);
        BigDecimal expected = new BigDecimal("100000.00");
        BigDecimal portfolioValue = service.getPortfolioValue()
                .setScale(2, RoundingMode.HALF_UP);

        assertEquals(expected, portfolioValue);
    }

    @DisplayName("Should return sorted cryptos using service.getSortedCryptos(\"price\")")
    @Test
    void shouldReturnSortedCryptos() {
        List<Crypto> cryptos = List.of(
                new Crypto(1, "Bitcoin", "BTC", BigDecimal.valueOf(300.0), 20.0), //6000
                new Crypto(2, "Ethereum", "ETH", BigDecimal.valueOf(1000.0), 14.0), //14000
                new Crypto(3, "Ripple", "XRP", BigDecimal.valueOf(4000.0), 20.0) //80000
        );
        Mockito.when(repository.findAll(Sort.by("price"))).thenReturn(cryptos);
        List<Crypto> sorted = service.getSortedCryptos("price");

        assertEquals("Bitcoin", sorted.get(0).getName());
        assertEquals("Ethereum", sorted.get(1).getName());
        assertEquals("Ripple", sorted.get(2).getName());
        Mockito.verify(repository).findAll(Sort.by("price"));
    }
}
