package put.poznan.spring_vue.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.reservation.Reservation;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT gs FROM Guest gs WHERE gs.id = :gs_id")
    Guest findGuestByReservationID(@Param("gs_id") int gs_id);

    @Query("SELECT rs FROM Reservation rs WHERE rs.id = :rs_id")
    Reservation findReservationByReservationID(@Param("rs_id") int rs_id);

    List<Order> findOrderByReservationId(int reservationID);

    void deleteById(int orderID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_in_order WHERE product_in_order.ORDER_ID = :orderID", nativeQuery = true)
    void deleteProductInOrderByOrderID(int orderID);

}