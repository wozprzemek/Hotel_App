package put.poznan.spring_vue.order;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDetails {

    private Date timeOfOrder;
    private int reservationID;

    public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
}
