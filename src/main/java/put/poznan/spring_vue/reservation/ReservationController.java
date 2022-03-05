package put.poznan.spring_vue.reservation;

import org.apache.http.client.utils.DateUtils;
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
import put.poznan.spring_vue.roomInReservation.RoomInReservation;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public @ResponseBody ResponseEntity<List<ReservationGetter>> getAllReservations(@RequestParam(name = "reservationID", required = false) Long reservationID, @RequestParam(name = "orderID", required = false) Long orderID) {
        try {
            List<Reservation> reservations = new ArrayList<>();
            List<ReservationGetter> toReturn = new ArrayList<>();
            ReservationGetter reservationGetter;
            DateFormat formatter = new SimpleDateFormat("yy/MM/yyyy");

            if(reservationID == null && orderID == null){
                reservations.addAll(reservationRepository.findAll());

                for(int i=0; i<reservations.size(); i++){
                    reservationGetter = new ReservationGetter();
                    reservationGetter.setReservationID(reservations.get(i).getId());
                    reservationGetter.setGuestID(reservations.get(i).getGuest().getId());
                    reservationGetter.setNumberOfGuests(reservations.get(i).getNumberOfGuests());
                    reservationGetter.setStartDate(formatter.parse(formatter.format(reservations.get(i).getStartDate())));
                    reservationGetter.setEndDate(formatter.parse(formatter.format(reservations.get(i).getEndDate())));
                    reservationGetter.setPrice(reservations.get(i).getPrice());
                    reservationGetter.setPaymentMethod(reservations.get(i).getPaymentMethod().getPaymentMethodName());
                    reservationGetter.setReservationState(reservations.get(i).getReservationState().getReservationStateName());
                    toReturn.add(reservationGetter);
                }
            }
            else{
                if(orderID != null){
                    Order order = reservationRepository.findOrderByOrderID(Math.toIntExact(orderID));
                    reservations.add(order.getReservation());
                }
                else{
                    reservations.add(reservationRepository.findByReservationID(Math.toIntExact(reservationID)));
                }
                reservationGetter = new ReservationGetter();
                reservationGetter.setReservationID(reservations.get(0).getId());
                reservationGetter.setGuestID(reservations.get(0).getGuest().getId());
                reservationGetter.setNumberOfGuests(reservations.get(0).getNumberOfGuests());
                reservationGetter.setStartDate(reservations.get(0).getEndDate());
                reservationGetter.setEndDate(reservations.get(0).getEndDate());
                reservationGetter.setPrice(reservations.get(0).getPrice());
                reservationGetter.setPaymentMethod(reservations.get(0).getPaymentMethod().getPaymentMethodName());
                reservationGetter.setReservationState(reservations.get(0).getReservationState().getReservationStateName());
                toReturn.add(reservationGetter);
            }

            if (toReturn.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/history")
    public @ResponseBody ResponseEntity<List<ReservationGetter>> getReservationsHistory(@RequestParam(name = "roomNumber", required = false) Long roomNumber) {
        try {
            List<RoomInReservation> roomInReservations = reservationRepository.findAllRoomInReservation(Math.toIntExact(roomNumber));
            List<ReservationGetter> reservationGetterList = new ArrayList<>();

            for(int i=0; i<roomInReservations.size(); i++){
                ReservationGetter reservationGetter = new ReservationGetter();
                reservationGetter.setReservationID(roomInReservations.get(i).getReservation().getId());
                reservationGetter.setGuestID(roomInReservations.get(i).getGuest().getId());
                reservationGetter.setNumberOfGuests(roomInReservations.get(i).getReservation().getNumberOfGuests());
                reservationGetter.setStartDate(roomInReservations.get(i).getReservation().getStartDate());
                reservationGetter.setEndDate(roomInReservations.get(i).getReservation().getEndDate());
                reservationGetter.setPrice(roomInReservations.get(i).getReservation().getPrice());
                reservationGetter.setPaymentMethod(roomInReservations.get(i).getReservation().getPaymentMethod().getPaymentMethodName());
                reservationGetter.setReservationState(roomInReservations.get(i).getReservation().getReservationState().getReservationStateName());
                reservationGetterList.add(reservationGetter);
            }

            if (roomInReservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservationGetterList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/current")
    public @ResponseBody ResponseEntity<ReservationGetter> getCurrentReservation(@RequestParam(name = "roomNumber") Long roomNumber) {
        try {
            List<RoomInReservation> roomInReservations = reservationRepository.findAllRoomInReservation(Math.toIntExact(roomNumber));
            ReservationGetter reservationGetter = null;
            for(int i=0; i<roomInReservations.size(); i++){
                Date startDate = roomInReservations.get(i).getReservation().getStartDate();
                Date endDate = roomInReservations.get(i).getReservation().getEndDate();
                Date todayDate = new Date();
                if (todayDate.after(startDate) && endDate.after(todayDate)){
                    reservationGetter = new ReservationGetter();
                    reservationGetter.setReservationID(roomInReservations.get(i).getReservation().getId());
                    reservationGetter.setGuestID(roomInReservations.get(i).getGuest().getId());
                    reservationGetter.setNumberOfGuests(roomInReservations.get(i).getReservation().getNumberOfGuests());
                    reservationGetter.setStartDate(roomInReservations.get(i).getReservation().getStartDate());
                    reservationGetter.setEndDate(roomInReservations.get(i).getReservation().getEndDate());
                    reservationGetter.setPrice(roomInReservations.get(i).getReservation().getPrice());
                    reservationGetter.setPaymentMethod(roomInReservations.get(i).getReservation().getPaymentMethod().getPaymentMethodName());
                    reservationGetter.setReservationState(roomInReservations.get(i).getReservation().getReservationState().getReservationStateName());
                    break;
                }
            }
            if (reservationGetter == null) {
                return new ResponseEntity<>(reservationGetter = new ReservationGetter(), HttpStatus.OK);
            }
            return new ResponseEntity<>(reservationGetter, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}