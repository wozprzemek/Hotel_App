package put.poznan.spring_vue.guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.reservation.Reservation;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("SELECT ad FROM Address ad WHERE ad.id = :ad_id")
    Address findAddressByID(@Param("ad_id") int ad_id);

    @Query("SELECT rsv FROM Reservation rsv WHERE rsv.id = :res_id")
    Reservation findReservationByReservationID(@Param("res_id") int res_id);

    @Query("SELECT ord FROM Order ord WHERE ord.id = :ord_id")
    Order findOrderByOrderID(@Param("ord_id") int ord_id);

    @Query("SELECT ad FROM Address ad WHERE ad.streetName = :st_name AND ad.buildingNumber = :building_no")
    Address findAddress(@Param("st_name") String st_name, @Param("building_no") String building_no);
}