package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.Courier;
import pl.p32.app.model.Vehicle;
import pl.p32.app.model.repository.CourierRepository;

public class VehicleEditDialogController {

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private ComboBox<Courier> courierComboBox;

    private Stage dialogStage;
    private Vehicle vehicle;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        setupComboBox();

        brandTextField.setText(vehicle.getBrand());
        modelTextField.setText(vehicle.getModel());
        courierComboBox.setValue(vehicle.getCourier());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        vehicle.setBrand(brandTextField.getText());
        vehicle.setModel(modelTextField.getText());
        vehicle.getCourier().getVehicles().remove(vehicle);
        vehicle.setCourier(courierComboBox.getValue());
        vehicle.getCourier().getVehicles().add(vehicle);


        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }

    private void setupComboBox() {
        Callback<ListView<Courier>, ListCell<Courier>> courierFactory = new Callback<ListView<Courier>, ListCell<Courier>>() {
            @Override
            public ListCell<Courier> call(ListView<Courier> param) {
                return new ListCell<Courier>() {
                    @Override
                    protected void updateItem(Courier item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText("");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        courierComboBox.setButtonCell(courierFactory.call(null));
        courierComboBox.setCellFactory(courierFactory);
        courierComboBox.setItems(FXCollections.observableArrayList(CourierRepository.getInstance().findAll()));
    }
}
