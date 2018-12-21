package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Vehicle;
import pl.p32.app.model.repository.VehicleRepository;

public class VehicleOverviewController {

    private App app;
    private VehicleRepository repository;

    @FXML
    private TableView<Vehicle> vehicleTableView;
    @FXML
    private TableColumn<Vehicle, String> brandColumn;
    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private Label brandLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label courierLabel;

    @FXML
    public void initialize() {
        brandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));

        vehicleTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showVehicleDetails(newValue)));

        repository = VehicleRepository.getInstance();
        vehicleTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    private void showVehicleDetails(Vehicle vehicle) {
        if (vehicle != null) {
            brandLabel.setText(vehicle.getBrand());
            modelLabel.setText(vehicle.getModel());
            courierLabel.setText(
                    vehicle.getCourier() == null ? "Nieużywany" : vehicle.getCourier().getName()
            );
        } else {
            brandLabel.setText("");
            modelLabel.setText("");
            courierLabel.setText("");
        }
    }

    private void updateList() {
        vehicleTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    public void newVehicle() {
        Vehicle newVehicle = new Vehicle();
        boolean confirmed = app.showVehicleEditDialog(newVehicle);
        if (confirmed) {
            repository.save(newVehicle);
            updateList();
            showVehicleDetails(newVehicle);
        }
    }

    public void editVehicle() {
        Vehicle selected = vehicleTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showVehicleEditDialog(selected);
            if (confirmed) {
                repository.update(selected);
                updateList();
                showVehicleDetails(selected);
            }
        }
    }

    public void deleteVehicle() {
        Vehicle selected = vehicleTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showVehicleDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }

    @FXML
    public void setApp(App app) {
        this.app = app;
    }
}
