package pl.p32.app.model.repository;

import pl.p32.app.model.Shipment;

public class ShipmentRepository extends AbstractRepository<Shipment, Integer> {

    private static ShipmentRepository instance = new ShipmentRepository();

    public static ShipmentRepository getInstance() {
        return instance;
    }

    private ShipmentRepository() {
        super(Shipment.class);
    }
}
