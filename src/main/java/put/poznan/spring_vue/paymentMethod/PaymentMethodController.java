package put.poznan.spring_vue.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/pay") // This means URL's start with /demo (after Application path)
public class PaymentMethodController {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<PaymentMethod> addNewPaymentMethod (@RequestBody PaymentMethod paymentMethod) {
        try{
            PaymentMethod _paymentMethod = paymentMethodRepository.save(paymentMethod);
            return new ResponseEntity<>(_paymentMethod, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        try {
            List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>(paymentMethodRepository.findAll());
            if (paymentMethods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}