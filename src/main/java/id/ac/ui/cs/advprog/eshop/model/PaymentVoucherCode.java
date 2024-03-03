package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;

import java.util.Map;

public class PaymentVoucherCode extends Payment{
    public PaymentVoucherCode(String id, Order order, Map<String, String> paymentData) {
        super(id, order, paymentData);
    }

    @Override
    boolean paymentDataIsValid() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int numCharCount = 0;
        for (int i = 0; i < voucherCode.length(); i++){
            if (Character.isDigit(voucherCode.charAt(i))){
                numCharCount++;
            }
        }

        if (numCharCount != 8){
            return false;
        }


        return true;
    }
}
