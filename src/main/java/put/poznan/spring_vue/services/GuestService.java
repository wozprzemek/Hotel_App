package org.example;

import javax.persistence.*;
import java.text.Normalizer;
import java.sql.Date;
import java.util.List;

public class GuestService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        getGuests();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addGuest(String firstName, String lastName, String telephone, Date dateofbirth, int addressId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Guest guest = new Guest();
            guest.setFirstName(firstName);
            guest.setLastName(lastName);
            guest.setTelephone(telephone);
            guest.setDateOfBirth(dateofbirth);
            guest.setAddress_id(addressId);
            em.persist(guest);
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

    public static void getGuest(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT g FROM Guest g WHERE g.id = :GUEST_ID";

        TypedQuery<Guest> tq = em.createQuery(query, Guest.class);
        tq.setParameter("GUEST_ID", id);
        Guest guest = null;
        try {
            guest = tq.getSingleResult();
            System.out.println(guest.getFirstName() + " " + guest.getLastName());
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    public static void getGuests(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT g FROM Guest g WHERE g.id IS NOT NULL";
        TypedQuery<Guest> tq = em.createQuery(query, Guest.class);
        List<Guest> guests;
        try {
            guests = tq.getResultList();
            System.out.println();
            guests.forEach(guest-> System.out.println(guest.getFirstName() + " " + guest.getLastName()));
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally{
            em.close();
        }
    }

    public static void changeFirstName(int id, String firstName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Guest guest = null;
        try{
            et = em.getTransaction();
            et.begin();
            guest = em.find(Guest.class, id);
            guest.setFirstName(firstName);
            em.persist(guest);
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

    public static void deleteGuest(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Guest guest = null;
        try{
            et = em.getTransaction();
            et.begin();
            guest = em.find(Guest.class, id);
            em.remove(guest);
            em.persist(guest);
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
}
