package pl.p32.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@PrimaryKeyJoinColumn(name = "person_id")
public class Person extends Party {

    private String firstname;

    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public Person() {

    }

    public Person(String firstname, String lastname, String phone) {
        super(phone);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
