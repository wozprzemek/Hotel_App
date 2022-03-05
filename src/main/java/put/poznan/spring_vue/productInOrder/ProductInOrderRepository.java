package put.poznan.spring_vue.productInOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.product.Product;
import put.poznan.spring_vue.reservation.Reservation;
import put.poznan.spring_vue.room.Room;

import java.util.Date;
import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

    @Query("SELECT ord FROM Order ord WHERE ord.id = :ord_id")
    Order findOrderByID(@Param("ord_id") int ord_id);

    @Query("SELECT pr FROM Product pr WHERE pr.productName = :pr_name")
    Product findProductByName(@Param("pr_name") String pr_name);

    @Query("SELECT prodinord FROM ProductInOrder prodinord WHERE prodinord.order.id = :ord_id")
    List<ProductInOrder> findProductInOrderByOrderID(@Param("ord_id") int ord_id);

}