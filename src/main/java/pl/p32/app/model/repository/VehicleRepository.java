package pl.p32.app.model.repository;

import pl.p32.app.model.Vehicle;

public class VehicleRepository extends AbstractRepository<Vehicle> {

    private static VehicleRepository instance = new VehicleRepository();

    public static VehicleRepository getInstance() {
        return instance;
    }

    private VehicleRepository() {
        super(Vehicle.class);
    }
}
