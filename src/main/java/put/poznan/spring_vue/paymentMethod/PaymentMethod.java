package put.poznan.spring_vue.paymentMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_METHOD")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_METHOD_ID", unique = true)
    private int id;

    @Column(name = "PAYMENT_METHOD_NAME", nullable = false)
    private String paymentMethodName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
