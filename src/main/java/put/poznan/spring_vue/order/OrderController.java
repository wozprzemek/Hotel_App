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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public ResponseEntity<Order> addNewOrder (@RequestBody OrderDetails orderDetails) {
        try{
            Guest guest = orderRepository.findGuestByReservationID(orderDetails.getReservationID());
            Reservation reservation = orderRepository.findReservationByReservationID(orderDetails.getReservationID());
            Order order = new Order(0, orderDetails.getTimeOfOrder(), reservation, guest);
            orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}