package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;


import java.util.Map;


@Getter
public abstract class Payment {

    String id;
    Order order;
    Map<String, String> paymentData;

    String status;



    public Payment(String id, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.paymentData = paymentData;


        if (paymentDataIsValid()){
            setStatus("SUCCESS");
        } else{
            setStatus("REJECTED");
        }
    }
    public Payment(String id, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        this.paymentData = paymentData;
        setStatus(status);
    }
    public void setStatus(String status){
        if (PaymentStatus.contains(status)){
            this.status = status;

            if (status.equals("SUCCESS")){
                order.setStatus("SUCCESS");
            } else if (status.equals("REJECTED")){
                order.setStatus("FAILED");
            }

        } else{
            throw new IllegalArgumentException();
        }

    }

    abstract boolean paymentDataIsValid();
}
