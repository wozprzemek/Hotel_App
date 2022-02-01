package org.example;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable{

    @Id
    @Column(name = "CATEGORY_ID", unique = true)
    private int id;

    @Column(name = "CATEGORY_NAME", unique = true)
    private String categoryName;

    @Column(name = "START_SERVING_TIME", nullable = false)
    private int startServingTime;

    @Column(name = "END_SERVING_TIME", nullable = false)
    private int endServingTime;

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
