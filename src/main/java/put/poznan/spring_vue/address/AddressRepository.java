package put.poznan.spring_vue.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.admin.Admin;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT ct FROM City ct WHERE ct.cityName = :city_name")
    City findCityByName(@Param("city_name") String city_name);

}
