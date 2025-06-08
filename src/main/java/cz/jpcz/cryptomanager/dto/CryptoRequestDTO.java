package cz.jpcz.cryptomanager.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Symbol is required")
    @Size(max = 5, message = "Symbol cannot be longer than 5 characters")
    private String symbol;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Double quantity;

}
