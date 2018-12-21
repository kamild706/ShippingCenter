package pl.p32.app.model.repository;

import pl.p32.app.model.Enterprise;

public class EnterpriseRepository extends AbstractRepository<Enterprise> {

    private static EnterpriseRepository instance = new EnterpriseRepository();

    public static EnterpriseRepository getInstance() {
        return instance;
    }

    private EnterpriseRepository() {
        super(Enterprise.class);
    }
}
