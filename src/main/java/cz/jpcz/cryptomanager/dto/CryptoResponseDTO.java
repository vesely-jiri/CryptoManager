package cz.jpcz.cryptomanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoResponseDTO {

    private Integer id;
    private String name;
    private String symbol;
    private BigDecimal price;
    private Double quantity;

}
