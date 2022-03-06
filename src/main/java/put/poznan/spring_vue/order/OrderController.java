package put.poznan.spring_vue.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;
import put.poznan.spring_vue.roomInReservation.RoomInReservation;
import put.poznan.spring_vue.roomInReservation.RoomInReservationDetails;
import put.poznan.spring_vue.roomInReservation.RoomInReservationGetter;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/order") // This means URL's start with /demo (after Application path)
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    /***

        {
            "timeOfOrder": "2022-02-10T05:00",
            "reservationID": "1"
        }

     ***/

    @PostMapping(path="/add")
    public ResponseEntity<Integer> addNewOrder (@RequestBody OrderDetails orderDetails) {
        try{
            Reservation reservation = orderRepository.findReservationByReservationID(orderDetails.getReservationID());
            Guest guest = reservation.getGuest();
            Order order = new Order(0, orderDetails.getTimeOfOrder(), reservation, guest);
            order = orderRepository.save(order);
            return new ResponseEntity<>(order.getId(), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<OrderGetter>> getAllOrders(@RequestParam(name = "reservationID", required = false) Long reservationID) {
        try {
            List<Order> orders;
            if (reservationID != null) {
                orders = orderRepository.findOrderByReservationId(Math.toIntExact(reservationID));
            }else{
                orders = orderRepository.findAll();
            }
            List<OrderGetter> toReturn = new ArrayList<>();
            for(int i=0; i<orders.size(); i++){
                OrderGetter orderGetter = new OrderGetter();
                orderGetter.setOrderID(orders.get(i).getId());
                orderGetter.setTotalPrice(orders.get(i).getTotalOrderPrice());
                orderGetter.setTimeOfOrder(orders.get(i).getTimeOfOrder());

                toReturn.add(orderGetter);
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="/delete")
    @Transactional
    public ResponseEntity<Integer> deleteOrder(@RequestParam int orderID) {
        try {
            orderRepository.deleteProductInOrderByOrderID(orderID);
            orderRepository.deleteById(orderID);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}