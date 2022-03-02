package put.poznan.spring_vue.address;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable{

    @Id
    @Column(name = "COUNTRY_ID")
    private int countryID;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

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
}
