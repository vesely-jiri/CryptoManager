package cz.jpcz.cryptomanager.repository;

import cz.jpcz.cryptomanager.entity.Crypto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CryptoRepositoryTest {

    @Autowired
    private CryptoRepository repository;

    @Test
    void shouldFindAllCryptos() {
        repository.saveAll(List.of(
            new Crypto("Bitcoin", "BTC", BigDecimal.valueOf(300.0), 20.0),
            new Crypto("Ethereum", "ETH", BigDecimal.valueOf(1000.0), 14.0)
        ));

        List<Crypto> cryptos = repository.findAll();

        assertEquals(2, cryptos.size());
    }

    @Test
    void shouldSaveAndFindCrypto() {
        Crypto crypto = new Crypto("Bitcoin", "BTC", BigDecimal.valueOf(300.0), 20.0);
        Crypto saved = repository.save(crypto);

        Crypto found = repository.findById(saved.getId()).orElseThrow();
        assertEquals("Bitcoin", found.getName());
        assertEquals("BTC", found.getSymbol());
        assertEquals(BigDecimal.valueOf(300.0), found.getPrice());
        assertEquals(20.0, found.getQuantity());
    }
}
