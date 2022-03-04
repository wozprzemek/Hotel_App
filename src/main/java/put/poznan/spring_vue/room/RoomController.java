package put.poznan.spring_vue.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.hotel.Hotel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/room") // This means URL's start with /demo (after Application path)
public class RoomController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private RoomRepository roomRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<Room> addNewRoom (@RequestBody Room room, @RequestParam(name = "hotelID") int hotelID) {
        try{
            Hotel hotel = roomRepository.findHotelByID(hotelID);
            room.setHotel(hotel);
            Room _room = roomRepository.save(room);
            return new ResponseEntity<>(_room, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<RoomGetter>> getAllRooms() {
        RoomGetter roomGetter;
        List<RoomGetter> roomGetterList = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        try {
            for(int i=0;i<rooms.size(); i++) {
                roomGetter = new RoomGetter();
                roomGetter.setRoomName(rooms.get(i).getRoomName());
                roomGetter.setRoomNumber(rooms.get(i).getNumber());
                roomGetter.setFloor(rooms.get(i).getFloor());
                roomGetter.setPricePerNight(rooms.get(i).getPricePerNight());
                roomGetter.setSingleBeds(rooms.get(i).getSingleBeds());
                roomGetter.setDoubleBeds(rooms.get(i).getDoubleBeds());
                roomGetterList.add(roomGetter);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(roomGetterList, HttpStatus.OK);
    }

    @PostMapping(path="/av")
    public @ResponseBody ResponseEntity<List<List<Room>>> getAvailableRooms(@RequestBody RoomAvailability roomAvailability, HttpServletRequest request) {

        Date start_date = roomAvailability.getStartDate();
        Date end_date = roomAvailability.getEndDate();
        List<Integer> roomDetailsList = roomAvailability.getRoomsDetailsList();
        List<Integer> allNumbers = roomRepository.findAllNumbers();

        List<List<Room>> totalAvailableRooms = new ArrayList<>();
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        int number_of_people;

        System.out.println(roomAvailability.toString());

        for(int i=0; i<roomDetailsList.size(); i++){
            availableRooms.clear();
            number_of_people = roomDetailsList.get(i);
            for(int j=0; j<allNumbers.size(); j++){
                if (roomRepository.checkAvailability(allNumbers.get(j), start_date, end_date) && number_of_people <= roomRepository.checkCapacity(allNumbers.get(j))){
                    Room room = roomRepository.findByNumber(allNumbers.get(j));
                    availableRooms.add(room);
                }
            }
            ArrayList<Room> to_add = new ArrayList<Room>(availableRooms);
            totalAvailableRooms.add(to_add);
        }

        return new ResponseEntity<>(totalAvailableRooms, HttpStatus.OK);
    }

    @GetMapping(path="/details")
    public @ResponseBody ResponseEntity<RoomGetter> getRoomDetails(@RequestParam("roomNumber") int roomNumber) {

        RoomGetter roomGetter;
        try {
            roomGetter = new RoomGetter();
            Room room = roomRepository.findRoomByNumber(roomNumber);
            roomGetter.setRoomName(room.getRoomName());
            roomGetter.setRoomNumber(room.getNumber());
            roomGetter.setFloor(room.getFloor());
            roomGetter.setPricePerNight(room.getPricePerNight());
            roomGetter.setSingleBeds(room.getSingleBeds());
            roomGetter.setDoubleBeds(room.getDoubleBeds());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(roomGetter, HttpStatus.OK);
    }
}