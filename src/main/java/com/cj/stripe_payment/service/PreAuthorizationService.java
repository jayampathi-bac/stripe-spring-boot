package com.cj.stripe_payment.service;

public interface PreAuthorizationService {

    public boolean isPreAuthorizationRequired();

    public boolean verifyPreAuthorizationCode(String code);
}
