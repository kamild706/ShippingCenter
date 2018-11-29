package pl.p32.app.model.repository;

import pl.p32.app.model.Parcel;

public class ParcelRepository extends AbstractRepository<Parcel, Integer> {

    public ParcelRepository() {
        super(Parcel.class);
    }
}
