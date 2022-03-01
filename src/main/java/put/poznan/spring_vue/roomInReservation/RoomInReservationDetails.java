package put.poznan.spring_vue.roomInReservation;

import java.util.List;

public class RoomInReservationDetails {

    private int reservationID;
    private List<Integer> rooms;

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }
}
