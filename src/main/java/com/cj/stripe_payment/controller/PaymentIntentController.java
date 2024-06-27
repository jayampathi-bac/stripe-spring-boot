package com.cj.stripe_payment.controller;

import com.cj.stripe_payment.model.Response;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentIntentController {
    @PostMapping("/create-payment-intent")
    public Response createPaymentIntent(@RequestBody Map<String, Object> data) throws StripeException {
        String paymentMethodId = (String) data.get("payment_method_id");
        Long amount = (Long) data.get("amount");

        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount(amount * 100L)
                .setCurrency("usd")
                .setPaymentMethod(paymentMethodId)
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setConfirm(true)
                .setPaymentMethodOptions(
                        PaymentIntentCreateParams.PaymentMethodOptions.builder()
                                .setCard(
                                        PaymentIntentCreateParams.PaymentMethodOptions.Card.builder()
                                                .setRequestThreeDSecure(PaymentIntentCreateParams.PaymentMethodOptions.Card.RequestThreeDSecure.ANY)
                                                .build()
                                )
                                .build()
                )
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);

        if ("requires_action".equals(intent.getStatus())) {
            return new Response(intent.getId(), intent.getClientSecret(), true);
        }

        return new Response(intent.getId(), intent.getClientSecret(), false);
    }
}
