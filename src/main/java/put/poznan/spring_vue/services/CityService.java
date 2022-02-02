package put.poznan.spring_vue.services;

import put.poznan.spring_vue.entities.City;

import javax.persistence.*;
import java.text.Normalizer;
import java.sql.Date;
import java.util.List;

public class CityService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        addCity("SZCZECIN");
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCity(String cityName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            City city = new City();
            city.setCityName(cityName);
            em.persist(city);
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

    public static void getCity(String cityName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        City city = null;
        try {
            et = em.getTransaction();
            et.begin();
            city = em.find(City.class, cityName);
            System.out.println(city.getCityName());
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }
}
