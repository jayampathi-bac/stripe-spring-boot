package com.cj.stripe_payment.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotNull
    private Long amount;

    @Email
    private String email;

    @NotBlank
    private String productName;
}
