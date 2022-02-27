package put.poznan.spring_vue.reservationState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/rsvstate") // This means URL's start with /demo (after Application path)
public class ReservationStateController {
    @Autowired
    private ReservationStateRepository reservationStateRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<ReservationState> addNewReservationState (@RequestBody ReservationState reservationState) {
        try{
            ReservationState _reservationState = reservationStateRepository.save(reservationState);
            return new ResponseEntity<>(_reservationState, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<ReservationState>> getAllReservationStates() {
        try {
            List<ReservationState> reservationStates = new ArrayList<ReservationState>(reservationStateRepository.findAll());
            if (reservationStates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservationStates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}