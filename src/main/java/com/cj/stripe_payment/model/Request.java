package com.cj.stripe_payment.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotNull
    @DecimalMin(value = "0.5", message = "Minimum amount is 0.5")
    private BigDecimal amount;

    @Email
    private String email;

    @NotBlank
    private String productName;
}
