package put.poznan.spring_vue.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.hotel.Hotel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/rsv") // This means URL's start with /demo (after Application path)
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;


}