package put.poznan.spring_vue.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.category.Category;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.room.Room;

import java.util.ArrayList;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/api/product") // This means URL's start with /demo (after Application path)
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<Product> addNewProduct (@RequestBody Product product, @RequestParam(name = "categoryID") int categoryID) {
        try{
            Category category = productRepository.findCategoryByID(categoryID);
            product.setCategory(category);
            Product _product = productRepository.save(product);
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = new ArrayList<Product>(productRepository.findAll());
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}