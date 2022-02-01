package org.example;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable{

    @Id
    @Column(name = "COUNTRY_NAME", unique = true)
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
