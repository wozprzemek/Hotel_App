package put.poznan.spring_vue.reservationState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION_STATE")
public class ReservationState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_STATE_ID", unique = true)
    private int id;

    @Column(name = "RESERVATION_STATE_NAME", nullable = false)
    private String reservationStateName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservationStateName() {
        return reservationStateName;
    }

    public void setReservationStateName(String reservationStateName) {
        this.reservationStateName = reservationStateName;
    }
}
