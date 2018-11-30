package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.p32.app.App;
import pl.p32.app.model.Letter;
import pl.p32.app.model.Parcel;
import pl.p32.app.model.Shipment;
import pl.p32.app.model.ShipmentItem;
import pl.p32.app.model.repository.ShipmentRepository;

public class ShipmentOverviewController {

    private App app;
    private ShipmentRepository repository;

    @FXML
    private TableView<Shipment> shipmentTable;
    @FXML
    private TableColumn<Shipment, String> shipmentColumn;

    @FXML
    private Label senderLabel;
    @FXML
    private Label receiverLabel;
    @FXML
    private Label sentAtLabel;
    @FXML
    private Label receivedAtLabel;
    @FXML
    private Label courierLabel;
    @FXML
    private Label lettersLabel;
    @FXML
    private Label parcelsLabel;
    @FXML
    private Label warehouseLabel;

    @FXML
    public void initialize() {
        shipmentColumn.setCellValueFactory(cellData -> new SimpleStringProperty("Przesyłka " + cellData.getValue().getFormattedDateTime()));

        shipmentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showShipmentDetails(newValue)));

        repository = ShipmentRepository.getInstance();
        shipmentTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    public void showShipmentDetails(Shipment shipment) {
        if (shipment != null) {
            senderLabel.setText(shipment.getSender().getName());
            sentAtLabel.setText(shipment.getFormattedDateTime());
            receivedAtLabel.setText(shipment.getDeliveryDate() == null ? "Niedostarczona" : shipment.getDeliveryFormattedDateTime());
            courierLabel.setText(shipment.getCourier() == null ? "-" : shipment.getCourier().getName());
            warehouseLabel.setText(shipment.getWarehouse().getName());
            showShipmentItems(shipment);
        } else {
            senderLabel.setText("");
            sentAtLabel.setText("");
            receivedAtLabel.setText("");
            courierLabel.setText("");
            lettersLabel.setText("");
            parcelsLabel.setText("");
            warehouseLabel.setText("");
        }
    }

    private void showShipmentItems(Shipment shipment) {
        int letters = 0;
        int parcels = 0;

        for (ShipmentItem item : shipment.getItems()) {
            if (item instanceof Letter) letters++;
            if (item instanceof Parcel) parcels++;
        }

        lettersLabel.setText(String.valueOf(letters));
        parcelsLabel.setText(String.valueOf(parcels));
    }

    public void updateList() {
        shipmentTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    public void newShipment() {
        Shipment newShipment = new Shipment();
        boolean confirmed = app.showNewShipmentDialog(newShipment);
        if (confirmed) {
            repository.save(newShipment);
            updateList();
        }
    }

    public void setApp(App app) {
        this.app = app;
    }
}
