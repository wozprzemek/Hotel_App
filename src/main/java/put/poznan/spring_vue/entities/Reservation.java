package put.poznan.spring_vue.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public class Reservation {

    @Id
    @Column(name = "RESERVATION_ID", unique = true)
    private int id;

    @Column(name = "NUMBER_OF_gUESTS", nullable = false)
    private int numberOfGuests;

    @Column(name = "PRICE", nullable = false)
    private float price;

    @Column(name = "PRICE_PER_NIGHT", nullable = false)
    private float pricePerNight;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;
}
