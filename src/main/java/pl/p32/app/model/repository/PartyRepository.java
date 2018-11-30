package pl.p32.app.model.repository;

import pl.p32.app.model.Party;

public class PartyRepository extends AbstractRepository<Party, Integer> {

    private static PartyRepository instance = new PartyRepository();

    public static PartyRepository getInstance() {
        return instance;
    }

    private PartyRepository() {
        super(Party.class);
    }
}
