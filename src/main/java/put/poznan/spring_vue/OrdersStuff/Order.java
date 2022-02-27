package put.poznan.spring_vue.OrdersStuff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.io.Serializable;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable{

    @Id
    @Column(name = "ORDER_ID", unique = true)
    private int id;

    @Column(name = "TOTAL_ORDER_PRICE", nullable = false)
    private float totalOrderPrice;

    @Column(name = "TIME_OF_ORDER", nullable = false)
    private Date timeOfOrder;

    @Column(name = "RESERVATION_ID", nullable = false)
    private int reservationId;

    @Column(name = "GUEST_ID", nullable = false)
    private int guestId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(float totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
