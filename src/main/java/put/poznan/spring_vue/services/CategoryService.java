package org.example;

import javax.persistence.*;

public class CategoryService {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        addCategory("BREAKFAST", 8, 12);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCategory(String categoryName, int startServingTime, int endServingTime){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setStartServingTime(startServingTime);
            category.setEndServingTime(endServingTime);
            em.persist(category);
            et.commit();
        }
        catch(Exception ex){
            if (et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    public static void getCategoryServingTime(String categoryName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Category category = null;
        try {
            et = em.getTransaction();
            et.begin();
            category = em.find(Category.class, categoryName);
            System.out.println(category.getStartServingTime() + " " + category.getEndServingTime());
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }

}
