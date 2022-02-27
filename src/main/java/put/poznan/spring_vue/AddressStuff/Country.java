package put.poznan.spring_vue.AddressStuff;

import java.io.Serializable;
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
