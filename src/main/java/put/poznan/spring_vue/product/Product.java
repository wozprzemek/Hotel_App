package put.poznan.spring_vue.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.category.Category;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true)
    private int id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private float productPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
