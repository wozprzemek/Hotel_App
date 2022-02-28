package put.poznan.spring_vue.productInOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.product.Product;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;

import java.util.Date;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/prodinord") // This means URL's start with /demo (after Application path)
public class ProductInOrderController {
    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<ProductInOrder> addNewProductInOrder (@RequestBody ProductInOrderDetails productInOrderDetails) {
        try{
            for(int i=0; i<productInOrderDetails.getProducts().size(); i++){
                int quantity = productInOrderDetails.getProducts().get(i).getProductQuantity();
                float subtotal = productInOrderDetails.getProducts().get(i).getSubtotalPrice();
                Date serviceTime = productInOrderDetails.getProducts().get(i).getServiceTime();
                Order order = productInOrderRepository.findOrderByID(productInOrderDetails.getOrderID());
                Reservation reservation = order.getReservation();
                Guest guest = order.getGuest();
                Product product = productInOrderRepository.findProductByName(productInOrderDetails.getProducts().get(i).getProductName());
                ProductInOrder productInOrder = new ProductInOrder(quantity, subtotal, serviceTime, order, reservation, guest, product);
                productInOrderRepository.save(productInOrder);
            }
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}