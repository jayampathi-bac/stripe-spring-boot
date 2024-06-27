package com.cj.stripe_payment.controller;

import com.cj.stripe_payment.model.Request;
import com.cj.stripe_payment.model.Response;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class PaymentIntentController {
    @PostMapping("/create-payment-intent")
    public Response createPaymentIntent(@RequestBody Request request)
            throws StripeException {
        long amountInCents = request.getAmount().multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).longValue();

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amountInCents )
                        .putMetadata("productName",
                                request.getProductName())
                        .setCurrency("usd")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams
                                        .AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent intent =
                PaymentIntent.create(params);

        return new Response(intent.getId(),
                intent.getClientSecret());
    }
}
