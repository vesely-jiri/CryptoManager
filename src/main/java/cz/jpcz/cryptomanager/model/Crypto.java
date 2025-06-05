package cz.jpcz.cryptomanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Crypto implements Comparable<Crypto> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String symbol;
    private Double price;
    private Double quantity;

    @Override
    public int compareTo(Crypto crypto) {
        return name.compareTo(crypto.getName());
    }
}