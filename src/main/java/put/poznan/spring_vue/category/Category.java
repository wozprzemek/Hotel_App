package put.poznan.spring_vue.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", unique = true)
    private int id;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    @Column(name = "START_SERVING_TIME", nullable = false)
    private int startServingTime;

    @Column(name = "END_SERVING_TIME", nullable = false)
    private int endServingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStartServingTime() {
        return startServingTime;
    }

    public void setStartServingTime(int startServingTime) {
        this.startServingTime = startServingTime;
    }

    public int getEndServingTime() {
        return endServingTime;
    }

    public void setEndServingTime(int endServingTime) {
        this.endServingTime = endServingTime;
    }
}
