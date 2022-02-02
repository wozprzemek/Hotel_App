package put.poznan.spring_vue.services;

import put.poznan.spring_vue.entities.Order;

import javax.persistence.*;
import java.text.Normalizer;
import java.sql.Date;
import java.util.List;

public class OrderService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");

    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        addOrder(1,50, date, 1, 1);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addOrder(int id, float totalOrderPrice, Date timeOfOrder, int reservationId, int guestId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Order order = new Order();
            order.setId(id);
            order.setTotalOrderPrice(totalOrderPrice);
            order.setTimeOfOrder(timeOfOrder);
            order.setReservationId(reservationId);
            order.setGuestId(guestId);
            em.persist(order);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }
}
