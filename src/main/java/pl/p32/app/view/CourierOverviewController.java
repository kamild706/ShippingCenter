package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.*;
import pl.p32.app.model.repository.ComplaintRepository;
import pl.p32.app.model.repository.CourierRepository;

import java.util.Collections;

public class CourierOverviewController {

    private App app;
    private CourierRepository repository;

    @FXML
    private TableView<Courier> courierTableView;

    @FXML
    private TableColumn<Courier, String> firstnameColumn;

    @FXML
    private TableColumn<Courier, String> lastnameColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private TableView<Shipment> shipmentsTableView;
    @FXML
    private TableColumn<Shipment, String> shipmentNameColumn;
    @FXML
    private TableView<Warehouse> warehousesTableView;
    @FXML
    private TableColumn<Warehouse, String> warehouseNameColumn;
    @FXML
    private TableView<Vehicle> vehicleTableView;
    @FXML
    private TableColumn<Vehicle, String> vehicleBrandTableColumn;
    @FXML
    private TableColumn<Vehicle, String> vehicleModelTableColumn;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        firstnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));

        courierTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showCourierDetails(newValue)));

        repository = CourierRepository.getInstance();
        courierTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
        shipmentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        warehouseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        vehicleBrandTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        vehicleModelTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
    }

    private void showCourierDetails(Courier courier) {
        if (courier != null) {
            firstnameLabel.setText(courier.getFirstname());
            lastnameLabel.setText(courier.getLastname());
            warehousesTableView.setItems(FXCollections.observableArrayList(courier.getWarehouses()));
            shipmentsTableView.setItems(FXCollections.observableArrayList(courier.getDeliveredShipments()));
            vehicleTableView.setItems(FXCollections.observableArrayList(courier.getVehicles()));
        } else {
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            warehousesTableView.getItems().clear();
            shipmentsTableView.getItems().clear();
            vehicleTableView.getItems().clear();
        }
    }

    private void updateList() {
        courierTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    @FXML
    public void deleteCourier() {
        Courier selected = courierTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showCourierDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }

    @FXML
    public void newCourier() {
        Courier object = new Courier();
        boolean confirmed = app.showCourierEditDialog(object);
        if (confirmed) {
            repository.save(object);
            updateList();
        }
    }

    @FXML
    public void editCourier() {
        Courier selected = courierTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showCourierEditDialog(selected);
            if (confirmed) {
                repository.update(selected);
                updateList();
                showCourierDetails(selected);
            }
        }
    }
}
