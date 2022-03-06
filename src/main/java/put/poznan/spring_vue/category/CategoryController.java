package put.poznan.spring_vue.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.spring_vue.product.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/cat")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path="/add")
    public ResponseEntity<Integer> addNewCategory(@RequestParam("category") String category) {
        try{
            Category existingCategory = categoryRepository.findByCategoryName(category);
            if(existingCategory == null){
                Category _category = new Category();
                _category.setCategoryName(category);
                _category.setStartServingTime(0);
                _category.setEndServingTime(24);
                categoryRepository.save(_category);
                return new ResponseEntity<>(1, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(0, HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> reservationStates = new ArrayList<Category>(categoryRepository.findAll());
            if (reservationStates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservationStates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/products")
    public @ResponseBody ResponseEntity<List<CategoryWithProducts>> getAllCategoriesWithProducts() {
        try {
            List<CategoryWithProducts> allCategoriesWithProducts = new ArrayList<>();
            CategoryWithProducts categoryWithProducts;
            List<Product> allProductsFromCategory;
            List<Category> allCategories = categoryRepository.findAll();

            for(int i=0; i<allCategories.size(); i++){
                allProductsFromCategory = categoryRepository.findAllProducts(allCategories.get(i).getCategoryName());
                categoryWithProducts = new CategoryWithProducts(allCategories.get(i).getCategoryName(), allProductsFromCategory);
                allCategoriesWithProducts.add(categoryWithProducts);
            }
            return new ResponseEntity<>(allCategoriesWithProducts, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}