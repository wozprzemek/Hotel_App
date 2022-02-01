package put.poznan.spring_vue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/hello")
    public JsonNode getRooms() throws JsonProcessingException {
        return objectMapper.readTree("{\"name\": \"Przemek\"}");
    }
}
