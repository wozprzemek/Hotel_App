package put.poznan.spring_vue.reservation;

import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.paymentMethod.PaymentMethod;
import put.poznan.spring_vue.reservationState.ReservationState;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID", unique = true)
    private int id;

    @Column(name = "NUMBER_OF_GUESTS", nullable = false)
    private int numberOfGuests;

    @Column(name = "PRICE", nullable = false)
    private float price;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="GUEST_ID")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name="HOTEL_ID")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="HOTEL_ADDRESS_ID")
    private Address hotelAddress;

    @ManyToOne
    @JoinColumn(name="RESERVATION_STATE_ID")
    private ReservationState reservationState;

    @ManyToOne
    @JoinColumn(name="PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    public Reservation(int numberOfGuests, float price, Date startDate, Date endDate, Guest guest, Hotel hotel, Address hotelAddress, ReservationState reservationState, PaymentMethod paymentMethod) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.hotel = hotel;
        this.hotelAddress = hotelAddress;
        this.reservationState = reservationState;
        this.paymentMethod = paymentMethod;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Address getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(Address hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public ReservationState getReservationState() {
        return reservationState;
    }

    public void setReservationState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
