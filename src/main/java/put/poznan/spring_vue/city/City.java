package put.poznan.spring_vue.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.country.Country;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "CITY")
public class City implements Serializable{

    @Id
    @Column(name = "CITY_ID")
    private int cityID;

    @Column(name = "CITY_NAME")
    private String cityName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    private Country country;

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
