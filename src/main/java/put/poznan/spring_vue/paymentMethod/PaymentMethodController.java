package put.poznan.spring_vue.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.category.Category;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/pay") // This means URL's start with /demo (after Application path)
public class PaymentMethodController {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PostMapping(path="/add")
    public ResponseEntity<Integer> addNewCategory(@RequestParam("paymentMethodName") String paymentMethodName) {
        try{
            PaymentMethod existingPaymentMethod = paymentMethodRepository.findByPaymentMethodName(paymentMethodName);
            if(existingPaymentMethod == null){
                PaymentMethod _paymentMethod = new PaymentMethod();
                _paymentMethod.setPaymentMethodName(paymentMethodName);
                paymentMethodRepository.save(_paymentMethod);
                return new ResponseEntity<>(1, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(0, HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<String>> getAllPaymentMethods() {
        try {
            List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>(paymentMethodRepository.findAll());
            List<String> toReturn = new ArrayList<>();
            for(int i=0; i<paymentMethods.size(); i++){
                toReturn.add(paymentMethods.get(i).getPaymentMethodName());
            }
            if (toReturn.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/details")
    public @ResponseBody ResponseEntity<List<PaymentMethod>> getAllPaymentMethodsWithDetails() {
        try {
            List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>(paymentMethodRepository.findAll());
            if (paymentMethods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}