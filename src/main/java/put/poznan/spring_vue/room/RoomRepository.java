package put.poznan.spring_vue.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByNumber(int number);

    @Query("SELECT ht FROM Hotel ht WHERE ht.id = :ht_id")
    Hotel findHotelByID(@Param("ht_id") int ht_id);

    @Query("SELECT number FROM Room")
    List<Integer> findAllNumbers();

    @Query(value = "SELECT AVAILABILITY(:rm_no, :start_date, :end_date) FROM DUAL", nativeQuery = true)
    boolean checkAvailability(@Param("rm_no") int rm_no, @Param("start_date") Date start_date,@Param("end_date") Date end_date);

    @Query(value = "SELECT CALCULATE_CAPACITY(:rm_no) FROM DUAL", nativeQuery = true)
    Integer checkCapacity(@Param("rm_no") int rm_no);

    Room findRoomByNumber(int room_number);

}