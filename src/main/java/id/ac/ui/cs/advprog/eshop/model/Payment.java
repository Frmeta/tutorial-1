package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class Payment {

    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    Order order;


    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        return;
    }
    public void setStatus(String status){
        return;
    }
}
