package put.poznan.spring_vue.room;

import java.util.Date;
import java.util.List;

public class RoomAvailability {

    private Date startDate;
    private Date endDate;
    private List<Integer> roomsDetailsList;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getRoomsDetailsList() {
        return roomsDetailsList;
    }

    public void setRoomsDetailsList(List<Integer> roomsDetailsList) {
        this.roomsDetailsList = roomsDetailsList;
    }
}
