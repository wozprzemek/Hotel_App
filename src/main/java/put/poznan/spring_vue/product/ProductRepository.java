package put.poznan.spring_vue.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import put.poznan.spring_vue.category.Category;
import put.poznan.spring_vue.hotel.Hotel;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT ct FROM Category ct WHERE ct.id = :ct_id")
    Category findCategoryByID(@Param("ct_id") int ct_id);

    @Query("SELECT ct FROM Category ct WHERE ct.categoryName = :ct_name")
    Category findCategoryByName(@Param("ct_name") String ct_name);

    Product findByProductName(String productName);

}