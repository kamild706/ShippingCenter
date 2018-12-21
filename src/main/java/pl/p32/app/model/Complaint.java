package pl.p32.app.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue
    private Integer id;

    private String comments;

    @Column(name = "receiving_date")
    private LocalDateTime receivingDate;

    @ManyToOne()
    @JoinColumn(name = "party_id")
    private Party creator;

    @ManyToOne()
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDateTime receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Party getCreator() {
        return creator;
    }

    public void setCreator(Party creator) {
        this.creator = creator;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        return receivingDate.format(formatter);
    }

    public String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm");
        return receivingDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(id, complaint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
