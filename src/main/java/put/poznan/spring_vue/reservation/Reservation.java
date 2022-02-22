package put.poznan.spring_vue.reservation;

import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;
import java.sql.Date;

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
    private Address address;
}
