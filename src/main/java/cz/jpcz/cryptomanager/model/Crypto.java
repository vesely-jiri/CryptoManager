package cz.jpcz.cryptomanager.model;

import cz.jpcz.cryptomanager.util.ConsoleColor;
import cz.jpcz.cryptomanager.util.DebugManager;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Crypto implements Comparable<Crypto> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String symbol;
    //BigDecimals?
    private Double price;
    private Double quantity;

    public Crypto(Integer id, String name, String symbol, Double price, Double quantity) {
        DebugManager.print(ConsoleColor.BLUE + "Crypto created with id " + id +
                ", name " + name + ", symbol " + symbol + ", price " + price + ", quantity " + quantity);
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(Crypto crypto) {
        return this.name.compareTo(crypto.name);
    }
}