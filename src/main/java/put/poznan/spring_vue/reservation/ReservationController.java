package put.poznan.spring_vue.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.admin.Admin;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.order.OrderDetails;
import put.poznan.spring_vue.paymentMethod.PaymentMethod;
import put.poznan.spring_vue.reservationState.ReservationState;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/rsv") // This means URL's start with /demo (after Application path)
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping(path="/add")
    public ResponseEntity<Integer> addNewReservation (@RequestBody ReservationDetails reservationDetails) {
        try{
            int numberOfGuests = reservationDetails.getNumberOfGuests();
            float price = 0;
            Date startDate = reservationDetails.getStartDate();
            Date endDate = reservationDetails.getEndDate();
            Guest guest = reservationRepository.findGuestByID(reservationDetails.getGuestID());
            Hotel hotel = reservationRepository.findHotelByID(1);
            Address hotelAddress = hotel.getAddress();
            ReservationState reservationState = reservationRepository.findReservationStateByID(1);
            PaymentMethod paymentMethod = reservationRepository.findPaymentMethodByName(reservationDetails.getPaymentMethodName());

            Reservation _reservation = new Reservation(numberOfGuests, price, startDate, endDate, guest, hotel, hotelAddress, reservationState, paymentMethod);
            reservationRepository.save(_reservation);
            return new ResponseEntity<>(_reservation.getId(), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = new ArrayList<Reservation>();
            reservations.addAll(reservationRepository.findAll());
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}