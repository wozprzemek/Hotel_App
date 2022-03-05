package put.poznan.spring_vue.reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationGetter {
    private int reservationID;
    private int guestID;
    private int numberOfGuests;
    private Date startDate;
    private Date endDate;
    private float price;
    private String paymentMethod;
    private String reservationState;

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Date getStartDate() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(startDate));
    }

    public void setStartDate(Date startDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.startDate = formatter.parse(formatter.format(startDate));
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.endDate = formatter.parse(formatter.format(endDate));
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }
}
