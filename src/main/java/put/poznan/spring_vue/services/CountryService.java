package org.example;

import javax.persistence.*;
import java.text.Normalizer;
import java.sql.Date;
import java.util.List;

public class CountryService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        addCountry("GERMANY");
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCountry(String countryName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Country country = new Country();
            country.setCountryName(countryName);
            em.persist(country);
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

    public static void getCountry(String countryName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Country country = null;
        try {
            et = em.getTransaction();
            et.begin();
            country = em.find(Country.class, countryName);
            System.out.println(country.getCountryName());
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }
}
