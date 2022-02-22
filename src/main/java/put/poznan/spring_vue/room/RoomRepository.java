package put.poznan.spring_vue.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByNumber(int number);

    @Query("SELECT ht FROM Hotel ht WHERE ht.id = :ht_id")
    Hotel findHotelByID(@Param("ht_id") int ht_id);
}