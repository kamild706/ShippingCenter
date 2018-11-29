package pl.p32.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courier")
public class Courier {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Shipment> deliveredShipments = new ArrayList<>();

    @ManyToMany(mappedBy = "couriers")
    private List<Warehouse> warehouses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setCourier(this);
    }

    public void addDeliveredShipment(Shipment shipment) {
        deliveredShipments.add(shipment);
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    public Courier() {

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
}
