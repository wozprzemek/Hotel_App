package put.poznan.spring_vue.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/admin") // This means URL's start with /demo (after Application path)
public class AdminController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AdminRepository adminRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<Admin> addNewAdmin (@RequestBody Admin admin) {
        /*** SAMPLE INPUT
        {
            "login":"logincurlnowy",
            "password":"haslocurlnowe",
            "firstName":"Jakub",
            "lastName":"Wrobel",
            "lastLogin":"2012-04-23T18:25:43.511Z"
        }
         ***/
        try{
            Admin _admin = adminRepository.save(admin);
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<Admin>> getAllUsers(@RequestParam(name = "login", required = false) String login) {
        try {
            List<Admin> admins = new ArrayList<Admin>();
            if (login == null) {
                admins.addAll(adminRepository.findAll());
            } else {
                admins.addAll(adminRepository.findByLogin(login));
            }
            if (admins.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}