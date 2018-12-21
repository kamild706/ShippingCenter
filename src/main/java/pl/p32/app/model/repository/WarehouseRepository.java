package pl.p32.app.model.repository;

import pl.p32.app.model.Warehouse;

public class WarehouseRepository extends AbstractRepository<Warehouse> {

    private static WarehouseRepository instance = new WarehouseRepository();

    public static WarehouseRepository getInstance() {
        return instance;
    }

    private WarehouseRepository() {
        super(Warehouse.class);
    }
}
