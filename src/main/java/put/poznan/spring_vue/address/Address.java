package put.poznan.spring_vue.address;

import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.room.Room;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

    @Id
    @Column(name = "ADDRESS_ID", unique = true)
    private int id;

    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    @Column(name = "BUILDING_NUMBER", nullable = false)
    private String firstName;

    @Column(name = "APARTMENT_NUMBER")
    private String lastName;

    @OneToMany(mappedBy = "address")
    private List<Hotel> hotels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
