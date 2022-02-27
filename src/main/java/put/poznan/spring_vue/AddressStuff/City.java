package put.poznan.spring_vue.AddressStuff;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "CITY")
public class City implements Serializable{

    @Id
    @Column(name = "CITY_NAME", unique = true)
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
