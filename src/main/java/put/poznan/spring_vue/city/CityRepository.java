package put.poznan.spring_vue.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.admin.Admin;
import put.poznan.spring_vue.country.Country;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT ct FROM Country ct WHERE ct.countryName = :countryName")
    Country findCountryByCountryName(@Param("countryName") String countryName);

    City findCityByCityName(String cityName);

}
