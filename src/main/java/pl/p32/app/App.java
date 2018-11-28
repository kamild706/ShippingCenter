package pl.p32.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.p32.app.model.Courier;
import pl.p32.app.model.Warehouse;

public class App {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        System.out.println("sdfddfdffsdffaf");
        /*Courier courier = new Courier();
        courier.setFirstname("Kamil");
        courier.setLastname("Domurat");

        Warehouse warehouse = new Warehouse();
        warehouse.setName("MAGAZYN W PCIMIU DOLNYM");
        warehouse.addCourier(courier);

        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(courier);
        session.getTransaction().commit();
        session.close();
        System.exit(0);*/
    }
}
