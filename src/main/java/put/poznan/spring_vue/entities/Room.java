package put.poznan.spring_vue.entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class Room {

    @Id
    @Column(name = "NUMBER", unique = true)
    private int number;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name = "FLOOR", nullable = false)
    private String floor;

    @Column(name = "PRICE_PER_NIGHT", nullable = false)
    private float pricePerNight;

    @Column(name = "SINGLE_BEDS", nullable = false)
    private int singleBeds;

    @Column(name = "DOUBLE_BEDS", nullable = false)
    private int doubleBeds;
}
