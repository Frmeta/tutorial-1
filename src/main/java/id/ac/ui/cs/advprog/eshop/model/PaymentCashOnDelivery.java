package id.ac.ui.cs.advprog.eshop.model;


import lombok.Builder;

import java.util.Map;

public class PaymentCashOnDelivery extends Payment{
    public PaymentCashOnDelivery(String id, Order order, Map<String, String> paymentData) {
        super(id, order, paymentData);
    }

    @Override
    boolean paymentDataIsValid() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        return address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty();
    }
}

