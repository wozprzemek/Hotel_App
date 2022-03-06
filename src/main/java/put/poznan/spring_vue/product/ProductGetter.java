package put.poznan.spring_vue.product;

public class ProductGetter {
    private int id;
    private String productName;
    private float productPrice;
    private String categoryName;

    public ProductGetter(int id, String productName, float productPrice, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryName = categoryName;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
