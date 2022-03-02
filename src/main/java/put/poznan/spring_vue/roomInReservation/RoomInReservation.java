package put.poznan.spring_vue.roomInReservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.room.Room;

import javax.persistence.*;

@Entity
@Table(name = "ROOM_IN_RESERVATION")
public class RoomInReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_IN_RESERVATION_ID")
    private int id;

    @Column(name = "ROOM_TOTAL_PRICE")
    private float roomTotalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ROOM_NUMBER")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="HOTEL_ID")
    private Hotel hotel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="RESERVATION_ID")
    private Reservation reservation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="GUEST_ID")
    private Guest guest;

    public RoomInReservation(float roomTotalPrice, Room room, Hotel hotel, Reservation reservation, Guest guest) {
        this.roomTotalPrice = roomTotalPrice;
        this.room = room;
        this.hotel = hotel;
        this.reservation = reservation;
        this.guest = guest;
    }

    public RoomInReservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRoomTotalPrice() {
        return roomTotalPrice;
    }

    public void setRoomTotalPrice(float roomTotalPrice) {
        this.roomTotalPrice = roomTotalPrice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
}
