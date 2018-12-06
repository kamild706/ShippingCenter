package pl.p32.app.model.repository;

import pl.p32.app.model.Courier;

public class CourierRepository extends AbstractRepository<Courier, Integer> {
    private static CourierRepository instance = new CourierRepository();

    public static CourierRepository getInstance() {
        return instance;
    }

    private CourierRepository() {
        super(Courier.class);
    }
}
