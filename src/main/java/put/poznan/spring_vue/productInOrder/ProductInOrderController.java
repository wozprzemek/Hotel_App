package put.poznan.spring_vue.productInOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.admin.Admin;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.guest.GuestGetter;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.product.Product;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/prodinord") // This means URL's start with /demo (after Application path)
public class ProductInOrderController {
    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<List<ProductInOrder>> addNewProductInOrder (@RequestBody ProductInOrderDetails productInOrderDetails) {
        try{
            List<ProductInOrder> productInOrderList = new ArrayList<>();
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
                productInOrderList.add(productInOrder);
            }
            return new ResponseEntity<>(productInOrderList, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/details")
    public @ResponseBody ResponseEntity<List<ProductInProductInOrderDetails>> getProductInOrderDetails(@RequestParam(name = "orderID", required = false) Long orderID) {
        try {
            ProductInProductInOrderDetails productInProductInOrderDetails;
            List<ProductInOrder> productInOrderList = productInOrderRepository.findProductInOrderByOrderID(Math.toIntExact(orderID));
            List<ProductInProductInOrderDetails> toReturn = new ArrayList<>();
            for(int i=0; i<productInOrderList.size(); i++){
                productInProductInOrderDetails = new ProductInProductInOrderDetails();
                productInProductInOrderDetails.setCategory(productInOrderList.get(i).getProduct().getCategory().getCategoryName());
                productInProductInOrderDetails.setProductName(productInOrderList.get(i).getProduct().getProductName());
                productInProductInOrderDetails.setProductQuantity(productInOrderList.get(i).getProductQuantity());
                productInProductInOrderDetails.setServiceTime(productInOrderList.get(i).getServiceTime());
                productInProductInOrderDetails.setSubtotalPrice(productInOrderList.get(i).getProductSubtotalPrice());
                toReturn.add(productInProductInOrderDetails);
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}