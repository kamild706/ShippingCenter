package pl.p32.app.model;

import javax.persistence.*;

@Entity
@Table(name = "enterprise")
@PrimaryKeyJoinColumn(name = "enterprise_id")
public class Enterprise extends Party {

    @Column(name = "nip")
    private String NIP;

    private String name;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
