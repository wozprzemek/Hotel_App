package put.poznan.spring_vue.paymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.Date;
import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    PaymentMethod findByPaymentMethodName(String paymentMethodName);

}