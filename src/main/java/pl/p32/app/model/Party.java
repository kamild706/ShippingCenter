package pl.p32.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public class Party {

    @Id
    @GeneratedValue
    private Integer id;

    private String phone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "party_id")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shipment> sentShipments = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shipment> receivedShipments = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> filledComplaints = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Shipment> getSentShipments() {
        return sentShipments;
    }

    public void setSentShipments(List<Shipment> sentShipments) {
        this.sentShipments = sentShipments;
    }

    public List<Shipment> getReceivedShipments() {
        return receivedShipments;
    }

    public void setReceivedShipments(List<Shipment> receivedShipments) {
        this.receivedShipments = receivedShipments;
    }

    public List<Complaint> getFilledComplaints() {
        return filledComplaints;
    }

    public void setFilledComplaints(List<Complaint> filledComplaints) {
        this.filledComplaints = filledComplaints;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public void addSentShipment(Shipment shipment) {
        sentShipments.add(shipment);
    }

    public void addReceivedShipment(Shipment shipment) {
        receivedShipments.add(shipment);
    }

    public void addComplaint(Complaint complaint) {
        filledComplaints.add(complaint);
    }

    public Party() {

    }

    public Party(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return null;
    }
}
