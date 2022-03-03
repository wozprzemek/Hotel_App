package put.poznan.spring_vue.roomInReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.guest.GuestGetter;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/roominrsv") // This means URL's start with /demo (after Application path)
public class RoomInReservationController {
    @Autowired
    private RoomInReservationRepository roomInReservationRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<List<Room>> addNewRoomInReservation (@RequestBody RoomInReservationDetails roomInReservationDetails) {
        try{
            int reservationID = roomInReservationDetails.getReservationID();
            Reservation reservation = roomInReservationRepository.findReservationByID(reservationID);
            Hotel hotel = reservation.getHotel();
            Guest guest = reservation.getGuest();
            List<Integer> rooms = roomInReservationDetails.getRooms();

            RoomInReservation roomInReservation;
            List<Room> toReturn = new ArrayList<>();

            for(int i=0; i<rooms.size(); i++){
                Room roomToAdd = roomInReservationRepository.findRoomByNumber(rooms.get(i));
                roomInReservation = new RoomInReservation(0, roomToAdd, hotel, reservation, guest);
                RoomInReservation _roomInReservation = roomInReservationRepository.save(roomInReservation);
                roomInReservationRepository.setFullRoomPrice(reservation.getStartDate(), reservation.getEndDate(), rooms.get(i), _roomInReservation.getId());
                roomInReservation.setRoomTotalPrice(roomInReservationRepository.findById(_roomInReservation.getId()).getRoomTotalPrice());
                toReturn.add(roomInReservation.getRoom());
            }

            return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<RoomInReservationGetter> getAllRooms(@RequestParam(name = "reservationID", required = false) Long reservationID) {
        try {
            RoomInReservation roomInReservation = roomInReservationRepository.getById(reservationID);
            RoomInReservationGetter roomInReservationGetter = new RoomInReservationGetter();
            roomInReservationGetter.setRoomNumber(roomInReservation.getRoom().getNumber());
            roomInReservationGetter.setRoomName(roomInReservation.getRoom().getRoomName());
            roomInReservationGetter.setPricePerNight(roomInReservation.getRoom().getPricePerNight());
            roomInReservationGetter.setTotalPrice(roomInReservation.getRoomTotalPrice());
            roomInReservationGetter.setSingleBeds(roomInReservation.getRoom().getSingleBeds());
            roomInReservationGetter.setSingleBeds(roomInReservation.getRoom().getDoubleBeds());

            return new ResponseEntity<>(roomInReservationGetter, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}