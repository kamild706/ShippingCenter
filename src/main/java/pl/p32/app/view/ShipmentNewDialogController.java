package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.Party;
import pl.p32.app.model.Shipment;
import pl.p32.app.model.Warehouse;
import pl.p32.app.model.repository.PartyRepository;
import pl.p32.app.model.repository.WarehouseRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class ShipmentNewDialogController implements Initializable {

    @FXML
    private ComboBox<Party> senderCombo;

    @FXML
    private ComboBox<Party> receiverCombo;

    @FXML
    private ComboBox<Warehouse> warehouseCombo;


    private Stage dialogStage;
    private Shipment shipment;
    private boolean confirmed = false;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;

    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Callback<ListView<Party>, ListCell<Party>> partyFactory = new Callback<ListView<Party>, ListCell<Party>>() {
            @Override
            public ListCell<Party> call(ListView<Party> param) {
                return new ListCell<Party>() {
                    @Override
                    protected void updateItem(Party item, boolean empty) {
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

        Callback<ListView<Warehouse>, ListCell<Warehouse>> warehouseFactory = new Callback<ListView<Warehouse>, ListCell<Warehouse>>() {
            @Override
            public ListCell<Warehouse> call(ListView<Warehouse> param) {
                return new ListCell<Warehouse>() {
                    @Override
                    protected void updateItem(Warehouse item, boolean empty) {
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

        senderCombo.setButtonCell(partyFactory.call(null));
        senderCombo.setCellFactory(partyFactory);
        senderCombo.setItems(FXCollections.observableArrayList(PartyRepository.getInstance().findAll()));

        receiverCombo.setButtonCell(partyFactory.call(null));
        receiverCombo.setCellFactory(partyFactory);
        receiverCombo.setItems(FXCollections.observableArrayList(PartyRepository.getInstance().findAll()));

        warehouseCombo.setButtonCell(warehouseFactory.call(null));
        warehouseCombo.setCellFactory(warehouseFactory);
        warehouseCombo.setItems(FXCollections.observableArrayList(WarehouseRepository.getInstance().findAll()));
    }
}
