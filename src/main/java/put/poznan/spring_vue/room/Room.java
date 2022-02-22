package put.poznan.spring_vue.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @Column(name = "ROOM_NUMBER", unique = true)
    private int number;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name = "FLOOR", nullable = false)
    private String floor;

    @Column(name = "PRICE_PER_NIGHT", nullable = false)
    private float pricePerNight;

    @Column(name = "SINGLE_BEDS", nullable = false)
    private int singleBeds;

    @Column(name = "DOUBLE_BEDS", nullable = false)
    private int doubleBeds;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="HOTEL_ID")
    private Hotel hotel;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getSingleBeds() {
        return singleBeds;
    }

    public void setSingleBeds(int singleBeds) {
        this.singleBeds = singleBeds;
    }

    public int getDoubleBeds() {
        return doubleBeds;
    }

    public void setDoubleBeds(int doubleBeds) {
        this.doubleBeds = doubleBeds;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
