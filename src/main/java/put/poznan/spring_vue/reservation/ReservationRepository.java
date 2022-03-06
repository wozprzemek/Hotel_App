package put.poznan.spring_vue.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.address.Address;
import put.poznan.spring_vue.guest.Guest;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.order.Order;
import put.poznan.spring_vue.paymentMethod.PaymentMethod;
import put.poznan.spring_vue.reservationState.ReservationState;
import put.poznan.spring_vue.room.Room;
import put.poznan.spring_vue.roomInReservation.RoomInReservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT gs FROM Guest gs WHERE gs.id = :gs_id")
    Guest findGuestByID(@Param("gs_id") int gs_id);

    @Query("SELECT ht FROM Hotel ht WHERE ht.id = :ht_id")
    Hotel findHotelByID(@Param("ht_id") int ht_id);

    @Query("SELECT rs FROM ReservationState rs WHERE rs.id = :rs_id")
    ReservationState findReservationStateByID(@Param("rs_id") int rs_id);

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.paymentMethodName = :pm_name")
    PaymentMethod findPaymentMethodByName(@Param("pm_name") String pm_name);

    @Query("SELECT rsv FROM Reservation rsv WHERE rsv.id = :id")
    Reservation findByReservationID(int id);

    @Query("SELECT roominrsv FROM RoomInReservation roominrsv WHERE roominrsv.room.number = :room_number")
    List<RoomInReservation> findAllRoomInReservation(@Param("room_number") int room_number);

    @Query("SELECT ord FROM Order ord WHERE ord.id = :ord_id")
    Order findOrderByOrderID(@Param("ord_id") int ord_id);

    @Query("SELECT state FROM ReservationState state WHERE state.reservationStateName = :stateName")
    ReservationState findReservationStateByName(@Param("stateName") String stateName);

}