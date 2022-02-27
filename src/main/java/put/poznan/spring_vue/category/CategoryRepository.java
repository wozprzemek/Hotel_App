package put.poznan.spring_vue.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.category.Category;
import put.poznan.spring_vue.hotel.Hotel;
import put.poznan.spring_vue.product.Product;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT product FROM Product product WHERE product.category.categoryName = :category_name")
    List<Product> findAllProducts(@Param("category_name") String category_name);

}