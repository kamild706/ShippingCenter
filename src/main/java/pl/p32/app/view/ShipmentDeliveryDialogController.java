package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.*;
import pl.p32.app.model.repository.PartyRepository;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ShipmentDeliveryDialogController {

    @FXML
    private ComboBox<Courier> courierComboBox;

    private Stage dialogStage;
    private Shipment shipment;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
        initialize();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {

        shipment.setCourier(courierComboBox.getValue());
        shipment.setDeliveryDate(LocalDateTime.now());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }

    private void initialize() {
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
        courierComboBox.setItems(FXCollections.observableArrayList(shipment.getWarehouse().getCouriers()));
        System.out.println(shipment == null);
    }
}
