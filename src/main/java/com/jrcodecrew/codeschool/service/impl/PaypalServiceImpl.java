package com.jrcodecrew.codeschool.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jrcodecrew.codeschool.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalServiceImpl implements PaypalService {

    @Autowired
    private APIContext apiContext;


    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public Map<String, String> getParamMap(String href) {

        URI uri = null;
        try {
            uri = new URI(href);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String query = uri.getQuery();

        // If there are no query parameters
        if (query == null || query.isEmpty()) {
            System.out.println("No query parameters found.");
            return null;
        }

        // Splitting the query string into individual parameters
        String[] queryParams = query.split("&");

        // Creating a map to store the parameters
        Map<String, String> paramMap = new java.util.HashMap<>();

        // Parsing and storing key-value pairs
        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            paramMap.put(key, value);
        }

        return paramMap;
    }
}