package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentCashOnDelivery;
import id.ac.ui.cs.advprog.eshop.model.PaymentVoucherCode;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment (String id, Order order, String method, Map<String, String> paymentData){
        if (paymentRepository.findById(id) == null){

            Payment payment;
            switch (method){
                case "VOUCHER_CODE":
                    payment = new PaymentVoucherCode(id, order, paymentData);
                    break;
                case "CASH_ON_DELIVERY":
                    payment = new PaymentCashOnDelivery(id, order, paymentData);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }
    public Payment setStatus(Payment payment, String status){
        Payment savedPayment = paymentRepository.findById(payment.getId());
        if (savedPayment != null){

            Payment newPayment;
            if (payment instanceof PaymentVoucherCode){
                newPayment = new PaymentVoucherCode(payment.getId(), payment.getOrder(), payment.getPaymentData());

            } else if (payment instanceof PaymentCashOnDelivery){
                newPayment = new PaymentCashOnDelivery(payment.getId(), payment.getOrder(), payment.getPaymentData());

            } else{
                // Unknown Payment type
                throw new IllegalArgumentException();
            }
            newPayment.setStatus(status);
            paymentRepository.save(newPayment);
            return newPayment;

        }else{
            throw new NoSuchElementException();
        }
    }
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }
    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayment();
    }
}
