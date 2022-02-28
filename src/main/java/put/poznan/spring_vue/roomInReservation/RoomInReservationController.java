package put.poznan.spring_vue.roomInReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/roominrsv") // This means URL's start with /demo (after Application path)
public class RoomInReservationController {
    @Autowired
    private RoomInReservationRepository roomInReservationRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<RoomInReservation> addNewRoomInReservation (@RequestBody RoomInReservationDetails roomInReservationDetails) {
        try{
            Room room = roomInReservationRepository.findRoomByNumber(roomInReservationDetails.getRoomNumber());
            Hotel hotel = roomInReservationRepository.findHotelByID(roomInReservationDetails.getHotelID());
            Reservation reservation = roomInReservationRepository.findReservationByID(roomInReservationDetails.getReservationID());
            Guest guest = roomInReservationRepository.findGuestByID(roomInReservationDetails.getGuestID());

            RoomInReservation roomInReservation = new RoomInReservation(0, room, hotel, reservation, guest);
            RoomInReservation _roomInReservation = roomInReservationRepository.save(roomInReservation);

            roomInReservationRepository.setFullRoomPrice(reservation.getStartDate(), reservation.getEndDate(), room.getNumber(), _roomInReservation.getId());
            _roomInReservation.setRoomTotalPrice(roomInReservationRepository.findById(_roomInReservation.getId()).getRoomTotalPrice());
            return new ResponseEntity<>(_roomInReservation, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}