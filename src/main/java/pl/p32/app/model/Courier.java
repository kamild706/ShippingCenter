package pl.p32.app.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "courier")
public class Courier {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
//            CascadeType.PERSIST,
            CascadeType.DETACH
    })
    @JoinTable(name = "courier_warehouse",
            joinColumns = @JoinColumn(name = "courier_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouses = new HashSet<>();

    @OneToMany(mappedBy = "courier")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "courier")
    private List<Shipment> deliveredShipments = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Shipment> getDeliveredShipments() {
        return deliveredShipments;
    }

    public void setDeliveredShipments(List<Shipment> deliveredShipments) {
        this.deliveredShipments = deliveredShipments;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        warehouse.getCouriers().add(this);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
        warehouse.getCouriers().remove(this);
    }

    public void replaceWarehouses(Set<Warehouse> warehouses) {
        for (Warehouse warehouse : this.warehouses) {
//            removeWarehouse(warehouse);
            warehouse.getCouriers().remove(this);
        }
        this.warehouses.clear();

        for (Warehouse warehouse: warehouses)
            addWarehouse(warehouse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(id, courier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getName();
    }
}
