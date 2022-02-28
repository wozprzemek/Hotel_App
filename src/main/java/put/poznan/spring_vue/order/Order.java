package put.poznan.spring_vue.order;

import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.reservation.Reservation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", unique = true)
    private int id;

    @Column(name = "TOTAL_ORDER_PRICE", nullable = false)
    private float totalOrderPrice;

    @Column(name = "TIME_OF_ORDER", nullable = false)
    private Date timeOfOrder;

    @ManyToOne
    @JoinColumn(name="RESERVATION_ID")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="GUEST_ID")
    private Guest guest;

    public Order() {

    }

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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Order(float totalOrderPrice, Date timeOfOrder, Reservation reservation, Guest guest) {
        this.totalOrderPrice = totalOrderPrice;
        this.timeOfOrder = timeOfOrder;
        this.reservation = reservation;
        this.guest = guest;
    }
}
