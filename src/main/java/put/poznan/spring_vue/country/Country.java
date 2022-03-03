package put.poznan.spring_vue.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.city.City;
import put.poznan.spring_vue.room.Room;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable{

    @Id
    @Column(name = "COUNTRY_ID")
    private int countryID;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
