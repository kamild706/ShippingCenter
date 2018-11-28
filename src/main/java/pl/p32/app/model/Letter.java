package pl.p32.app.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "letter")
@PrimaryKeyJoinColumn(name = "letter_id")
public class Letter extends ShipmentItem {

}
