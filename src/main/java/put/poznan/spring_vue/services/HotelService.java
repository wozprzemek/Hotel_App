package put.poznan.spring_vue.services;

import put.poznan.spring_vue.hotel.Hotel;

import javax.persistence.*;

public class HotelService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        getHotel(2);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addHotel(String hotelName, int hotelRating, String telephone, int addressId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Hotel hotel = new Hotel();
            hotel.setHotelName(hotelName);
            hotel.setHotelRating(hotelRating);
            hotel.setTelephone(telephone);
            hotel.setAddressId(addressId);
            em.persist(hotel);
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

    public static void getHotel(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Hotel hotel = null;
        try {
            et = em.getTransaction();
            et.begin();
            hotel = em.find(Hotel.class, id);
            System.out.println(hotel.getHotelName());
        }
        catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }
}
