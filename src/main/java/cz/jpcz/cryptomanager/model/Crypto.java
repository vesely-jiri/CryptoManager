package cz.jpcz.cryptomanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cryptos")
public class Crypto implements Comparable<Crypto> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    @Column(unique = true)
    private String symbol;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Double quantity;

    @Override
    public int compareTo(@NonNull Crypto crypto) {
        return name.compareTo(crypto.getName());
    }
}