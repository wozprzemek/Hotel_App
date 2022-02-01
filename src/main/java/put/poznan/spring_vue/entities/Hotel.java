package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.io.Serializable;

@Entity
@Table(name = "HOTEL")
public class Hotel implements Serializable{

    @Id
    @Column(name = "HOTEL_ID", unique = true)
    private int id;

    @Column(name = "HOTEL_NAME", nullable = false)
    private String hotelName;

    @Column(name = "HOTEL_RATING", nullable = false)
    private int hotelRating;

    @Column(name = "TELEPHONE", nullable = false)
    private String telephone;

    @Column(name = "ADDRESS_ID", nullable = false)
    private int addressId;

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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

}
