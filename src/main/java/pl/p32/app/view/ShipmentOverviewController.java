package pl.p32.app.view;

import javafx.beans.property.SimpleIntegerProperty;
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

import java.util.List;
import java.util.stream.Collectors;

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
    private TableView<Parcel> parcelsTableView;
    @FXML
    private TableColumn<Parcel, Integer> weightColumnName;
    @FXML
    private TableColumn<Parcel, Integer> lengthColumnName;
    @FXML
    private TableColumn<Parcel, Integer> heightColumnName;
    @FXML
    private TableColumn<Parcel, Integer> widthColumnName;

    @FXML
    public void initialize() {
        shipmentColumn.setCellValueFactory(cellData -> new SimpleStringProperty("Przesyłka " + cellData.getValue().getFormattedDateTime()));

        shipmentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showShipmentDetails(newValue)));

        repository = ShipmentRepository.getInstance();
        shipmentTable.setItems(FXCollections.observableArrayList(repository.findAll()));

        widthColumnName.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWidth()).asObject());
        heightColumnName.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHeight()).asObject());
        weightColumnName.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWeight()).asObject());
        lengthColumnName.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLength()).asObject());
    }

    private void showShipmentDetails(Shipment shipment) {
//        repository.getSession().refresh(shipment);
        if (shipment != null) {
            senderLabel.setText(shipment.getSender().getName());
            receiverLabel.setText(shipment.getReceiver().getName());
            sentAtLabel.setText(shipment.getFormattedDateTime());
            receivedAtLabel.setText(shipment.getDeliveryDate() == null ? "Niedostarczona" : shipment.getDeliveryFormattedDateTime());
            courierLabel.setText(shipment.getCourier() == null ? "-" : shipment.getCourier().getName());
            warehouseLabel.setText(shipment.getWarehouse().getName());
            showShipmentItems(shipment);
            List<Parcel> list = shipment.getItems().stream().filter(p -> p instanceof Parcel).map(p -> (Parcel) p).collect(Collectors.toList());
            parcelsTableView.setItems(FXCollections.observableArrayList(list));
        } else {
            senderLabel.setText("");
            receiverLabel.setText("");
            sentAtLabel.setText("");
            receivedAtLabel.setText("");
            courierLabel.setText("");
            lettersLabel.setText("");
            parcelsLabel.setText("");
            warehouseLabel.setText("");
            parcelsTableView.getItems().clear();
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

    private void updateList() {
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

    public void deliverShipment() {
        Shipment shipment = shipmentTable.getSelectionModel().getSelectedItem();
        if (shipment.getCourier() != null) return;

        boolean confirmed = app.showShipmentDeliveryDialog(shipment);
        if (confirmed) {
            repository.update(shipment);
            showShipmentDetails(shipment);
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void createComplaint() {
        Shipment shipment = shipmentTable.getSelectionModel().getSelectedItem();
        boolean confirmed = app.showNewComplaintDialog(shipment);
        if (confirmed) {
            repository.update(shipment);
        }
    }
}
