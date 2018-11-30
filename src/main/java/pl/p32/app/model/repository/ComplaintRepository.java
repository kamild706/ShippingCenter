package pl.p32.app.model.repository;

import pl.p32.app.model.Complaint;

public class ComplaintRepository extends AbstractRepository<Complaint, Integer> {

    private static ComplaintRepository instance = new ComplaintRepository();

    public static ComplaintRepository getInstance() {
        return instance;
    }

    private ComplaintRepository() {
        super(Complaint.class);
    }
}
