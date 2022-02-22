package put.poznan.spring_vue.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.room.Room;

import javax.persistence.*;
import java.sql.Date;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "HOTEL")
public class Hotel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_ID", unique = true)
    private int id;

    @Column(name = "HOTEL_NAME", nullable = false)
    private String hotelName;

    @Column(name = "HOTEL_RATING", nullable = false)
    private int hotelRating;

    @Column(name = "TELEPHONE", nullable = false)
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(name="ADDRESS_ID")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
