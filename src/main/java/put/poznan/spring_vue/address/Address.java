package put.poznan.spring_vue.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.city.City;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", unique = true)
    private int id;

    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    @Column(name = "BUILDING_NUMBER", nullable = false)
    private String buildingNumber;

    @Column(name = "APARTMENT_NUMBER")
    private String apartmentNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="CITY_ID")
    private City city;

    public Address(String streetName, String buildingNumber, String apartmentNumber, City city) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
    }

    public Address() {

    }

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

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
