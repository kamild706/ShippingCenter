package pl.p32.app.model.repository;

import pl.p32.app.model.Parcel;

public class ParcelRepository extends AbstractRepository<Parcel, Integer> {

    private static ParcelRepository instance = new ParcelRepository();

    public static ParcelRepository getInstance() {
        return instance;
    }

    private ParcelRepository() {
        super(Parcel.class);
    }
}
