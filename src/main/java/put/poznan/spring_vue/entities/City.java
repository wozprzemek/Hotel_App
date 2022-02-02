package put.poznan.spring_vue.entities;

import java.io.Serializable;
import java.sql.Date;
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
