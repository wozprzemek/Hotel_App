package put.poznan.spring_vue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = "[{\"PRICE\":200,\"END_DATE\":\"2020-05-15\",\"GUEST_ID\":1,\"HOTEL_ID\":1,\"START_DATE\":\"2020-05-01\",\"RESERVATION_ID\":1,\"HOTEL_ADDRESS_ID\":1,\"NUMBER_OF_GUESTS\":3,\"PAYMENT_METHOD_NAME\":\"CARD\",\"RESERVATION_STATE_NAME\":\"BOOKED\"},{\"PRICE\":500,\"END_DATE\":\"2020-07-19\",\"GUEST_ID\":2,\"HOTEL_ID\":1,\"START_DATE\":\"2020-07-12\",\"RESERVATION_ID\":2,\"HOTEL_ADDRESS_ID\":1,\"NUMBER_OF_GUESTS\":5,\"PAYMENT_METHOD_NAME\":\"CASH\",\"RESERVATION_STATE_NAME\":\"BOOKED\"},{\"PRICE\":800,\"END_DATE\":\"2021-01-15\",\"GUEST_ID\":3,\"HOTEL_ID\":1,\"START_DATE\":\"2021-01-12\",\"RESERVATION_ID\":3,\"HOTEL_ADDRESS_ID\":1,\"NUMBER_OF_GUESTS\":4,\"PAYMENT_METHOD_NAME\":\"CASH\",\"RESERVATION_STATE_NAME\":\"BOOKED\"},{\"PRICE\":1200,\"END_DATE\":\"2022-01-05\",\"GUEST_ID\":2,\"HOTEL_ID\":1,\"START_DATE\":\"2022-01-02\",\"RESERVATION_ID\":4,\"HOTEL_ADDRESS_ID\":1,\"NUMBER_OF_GUESTS\":7,\"PAYMENT_METHOD_NAME\":\"CARD\",\"RESERVATION_STATE_NAME\":\"CANCELED\"},{\"PRICE\":100,\"END_DATE\":\"2021-12-27\",\"GUEST_ID\":1,\"HOTEL_ID\":1,\"START_DATE\":\"2021-12-21\",\"RESERVATION_ID\":5,\"HOTEL_ADDRESS_ID\":1,\"NUMBER_OF_GUESTS\":5,\"PAYMENT_METHOD_NAME\":\"CARD\",\"RESERVATION_STATE_NAME\":\"COMPLETED\"}]";
    @PostMapping("/reservations")
    public JsonNode sendRoomData() throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    @GetMapping("/test")
    public void getRoomData(JsonNode jsonNode){
        System.out.println(jsonNode.toString());
    }
}



