package org.example;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "GUEST")
public class Guest implements Serializable{

    @Id
    @Column(name = "GUEST_ID", unique = true)
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "TELEPHONE", nullable = false)
    private String telephone;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dateOfBirth;

    @Column(name = "ADDRESS_ID", nullable = false)
    private int addressId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAddress_id() {
        return addressId;
    }

    public void setAddress_id(int address_id) {
        this.addressId = address_id;
    }
}
