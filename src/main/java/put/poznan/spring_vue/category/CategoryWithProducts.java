package put.poznan.spring_vue.category;

import put.poznan.spring_vue.product.Product;

import java.util.List;

public class CategoryWithProducts {

    private String category;
    private List<Product> products;

    public CategoryWithProducts(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
