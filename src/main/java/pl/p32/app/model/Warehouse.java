package pl.p32.app.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Shipment> shipments = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "warehouses")
    private Set<Courier> couriers = new HashSet<>();


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

    public Set<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public List<Shipment> getNotDeliveredShipments() {
        return getShipments().stream().filter(p -> p.getCourier() == null).collect(Collectors.toList());
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
