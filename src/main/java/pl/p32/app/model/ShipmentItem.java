package pl.p32.app.model;

import javax.persistence.*;

@Entity
@Table(name ="shipment_item")
@Inheritance(strategy = InheritanceType.JOINED)
public class ShipmentItem {

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
