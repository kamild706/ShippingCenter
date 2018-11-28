package pl.p32.app.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "send_at")
    private LocalDateTime sendDate;

    @Column(name = "delivered_at")
    private LocalDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Party sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Party receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipment_id")
    private List<ShipmentItem> items;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Party getSender() {
        return sender;
    }

    public void setSender(Party sender) {
        this.sender = sender;
        sender.addSentShipment(this);
    }

    public Party getReceiver() {
        return receiver;
    }

    public void setReceiver(Party receiver) {
        this.receiver = receiver;
        receiver.addReceivedShipment(this);
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        warehouse.addShipment(this);
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
        courier.addDeliveredShipment(this);
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<ShipmentItem> getItems() {
        return items;
    }

    public void setItems(List<ShipmentItem> items) {
        this.items = items;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void addItem(ShipmentItem item) {
        items.add(item);
    }

    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        complaint.setShipment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(id, shipment.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Shipment() {

    }
}
