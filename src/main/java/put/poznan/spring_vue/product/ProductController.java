package put.poznan.spring_vue.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.category.Category;
import put.poznan.spring_vue.country.Country;
import put.poznan.spring_vue.country.CountryDetails;
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
    public ResponseEntity<Integer> addNewProduct(@RequestParam("productName") String productName, @RequestParam("productPrice") float productPrice, @RequestParam("categoryName") String categoryName){
        try{
            Product existingProduct = productRepository.findByProductName(productName);
            Category category = productRepository.findCategoryByName(categoryName);
            if(existingProduct == null && category != null){
                Product _product = new Product();
                _product.setProductName(productName);
                _product.setProductPrice(productPrice);
                _product.setCategory(category);
                productRepository.save(_product);
                return new ResponseEntity<>(1, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(0, HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<ProductGetter>> getAllProducts() {
        try {
            List<Product> products = new ArrayList<Product>(productRepository.findAll());
            List<ProductGetter> productGetterList = new ArrayList<>();
            ProductGetter productGetter;
            for(int i=0; i<products.size(); i++){
                productGetter = new ProductGetter(products.get(i).getId(), products.get(i).getProductName(), products.get(i).getProductPrice(), products.get(i).getCategory().getCategoryName());
                productGetterList.add(productGetter);
            }
            if (productGetterList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productGetterList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}