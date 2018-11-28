package pl.p32.app.model;

import javax.persistence.*;

@Entity
@Table(name = "enterprise")
@PrimaryKeyJoinColumn(name = "enterprise_id")
public class Enterprise extends Party {

    @Column(name = "nip")
    private String NIP;

    private String name;
}
