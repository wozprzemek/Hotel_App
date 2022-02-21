package put.poznan.spring_vue.services;

import put.poznan.spring_vue.entities.Admin;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class AdminService {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HotelManagementSystem");
    public static void main(String[] args){
        Date date = new Date(System.currentTimeMillis());
        addAdmin("loginadmina","hasloadmina", "Jakub", "Wróbel", date);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAdmin(String login, String password, String firstName, String lastName, Date lastLogin){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Admin admin = new Admin();
            admin.setLogin(login);
            admin.setPassword(password);
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setLastLogin(lastLogin);
            em.persist(admin);
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

    public static void validateCredentials(String login, String password){

        // TODO
        // code that validates signing in

    }

    public void addAdmin(Admin admin) {
    }
}
