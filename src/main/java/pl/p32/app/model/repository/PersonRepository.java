package pl.p32.app.model.repository;

import pl.p32.app.model.Person;

public class PersonRepository extends AbstractRepository<Person, Integer> {

    private static PersonRepository instance = new PersonRepository();

    public static PersonRepository getInstance() {
        return instance;
    }

    private PersonRepository() {
        super(Person.class);
    }

}


/*public class PersonRepository extends AbstractRepository<Person, Integer> {

    private static PersonRepository instance = new PersonRepository();

    public static PersonRepository getInstance() {
        return instance;
    }

    private PersonRepository() {
        super(Person.class);
    }

}*/
