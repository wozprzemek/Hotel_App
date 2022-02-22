package put.poznan.spring_vue.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT ad FROM Address ad WHERE ad.id = :ad_id")
    Address findAddressByID(@Param("ad_id") int ad_id);

}