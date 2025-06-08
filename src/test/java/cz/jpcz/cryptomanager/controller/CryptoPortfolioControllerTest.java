package cz.jpcz.cryptomanager.controller;

import cz.jpcz.cryptomanager.exception.CryptoNotFoundException;
import cz.jpcz.cryptomanager.model.Crypto;
import cz.jpcz.cryptomanager.service.CryptoPortfolioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(CryptoPortfolioController.class)
public class CryptoPortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoPortfolioService service;

    @DisplayName("Should return crypto list using service.getAllCryptos()")
    @Test
    void shouldReturnCryptoList() throws Exception {
        List<Crypto> data = List.of(
                new Crypto(1, "Bitcoin", "BTC", 1000.0, 10.0),
                new Crypto(2, "Ethereum", "ETH", 2000.0, 20.0),
                new Crypto(3, "Ripple", "XRP", 3000.0, 30.0));
        Mockito.when(service.getAllCryptos()).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.get("/cryptos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("BTC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("ETH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].symbol").value("XRP"));
    }

    @DisplayName("Should return portfolio value using service.getPortfolioValue()")
    @Test
    void shouldReturnPortfolioValue() throws Exception {
        Mockito.when(service.getPortfolioValue()).thenReturn(17000.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/portfolio-value"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Portfolio value: 17000.0"));
    }

    @DisplayName("Should return 404 when crypto with id 999 not found")
    @Test
    void shouldReturn404() throws Exception {
        int id = 999;
        Mockito.when(service.getCrypto(id)).thenThrow(new CryptoNotFoundException("Crypto with id " + id + " not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cryptos/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Crypto with id " + id + " not found"));
    }
}
