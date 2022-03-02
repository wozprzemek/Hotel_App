package put.poznan.spring_vue.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/address") // This means URL's start with /demo (after Application path)
public class AddressController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AddressRepository addressRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<Integer> addNewAddress (@RequestBody AddressDetails addressDetails) {
        try{
            City city = addressRepository.findCityByName(addressDetails.getCityName());
            Address _address = new Address(addressDetails.getStreetName(), addressDetails.getBuildingNumber(), addressDetails.getApartmentNumber(), city);
            _address = addressRepository.save(_address);
            return new ResponseEntity<>(_address.getId(), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}