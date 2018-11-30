package pl.p32.app.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Shipment> shipments = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(name = "courier_warehouse",
            joinColumns = @JoinColumn(name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "courier_id")
    )
    private List<Courier> couriers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
        courier.addWarehouse(this);
    }

    public void removeCourier(Courier courier) {
        couriers.remove(courier);
        courier.removeWarehouse(this);
    }

    public Warehouse() {

    }

    public void addShipment(Shipment shipment) {
        shipments.add(shipment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
