package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.*;
import pl.p32.app.model.repository.PartyRepository;
import pl.p32.app.model.repository.WarehouseRepository;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ShipmentNewDialogController implements Initializable {

    @FXML
    private ComboBox<Party> senderCombo;

    @FXML
    private ComboBox<Party> receiverCombo;

    @FXML
    private ComboBox<Warehouse> warehouseCombo;

    @FXML
    private ComboBox<Address> addressCombo;

    @FXML
    private Label lettersCountLabel;

    @FXML
    private Label parcelsCountLabel;

    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField weightField;


    private Stage dialogStage;
    private Shipment shipment;
    private boolean confirmed = false;

    private int letters = 0;
    private int parcels = 0;


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
        shipment.setSender(senderCombo.getValue());
        shipment.setReceiver(receiverCombo.getValue());
        shipment.setSendDate(LocalDateTime.now());
        shipment.setDeliveryAddress(addressCombo.getValue());
        shipment.setWarehouse(warehouseCombo.getValue());
        shipment.getWarehouse().getShipments().add(shipment);
        shipment.getSender().getSentShipments().add(shipment);
        shipment.getReceiver().getReceivedShipments().add(shipment);

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }

    private void setLettersCountLabel(int num) {
        lettersCountLabel.setText(String.valueOf(num));
    }

    private void setParcelsCountLabel(int num) {
        parcelsCountLabel.setText(String.valueOf(num));
    }

    @FXML
    private void addParcel() {
        Parcel parcel = new Parcel();
        parcel.setHeight(Integer.valueOf(heightField.getText()));
        parcel.setWeight(Integer.valueOf(weightField.getText()));
        parcel.setLength(Integer.valueOf(lengthField.getText()));
        parcel.setWidth(Integer.valueOf(widthField.getText()));

        heightField.setText("");
        weightField.setText("");
        lengthField.setText("");
        widthField.setText("");

        shipment.addItem(parcel);
        setParcelsCountLabel(++parcels);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Callback<ListView<Party>, ListCell<Party>> senderFactory = new Callback<ListView<Party>, ListCell<Party>>() {
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

        Callback<ListView<Party>, ListCell<Party>> receiverFactory = new Callback<ListView<Party>, ListCell<Party>>() {
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
                            addressCombo.setItems(FXCollections.observableArrayList(item.getAddresses()));
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

        Callback<ListView<Address>, ListCell<Address>> addressFactory = new Callback<ListView<Address>, ListCell<Address>>() {
            @Override
            public ListCell<Address> call(ListView<Address> param) {
                return new ListCell<Address>() {
                    @Override
                    protected void updateItem(Address item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText("");
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        };

        senderCombo.setButtonCell(senderFactory.call(null));
        senderCombo.setCellFactory(senderFactory);
        senderCombo.setItems(FXCollections.observableArrayList(PartyRepository.getInstance().findAll()));

        receiverCombo.setButtonCell(receiverFactory.call(null));
        receiverCombo.setCellFactory(receiverFactory);
        receiverCombo.setItems(FXCollections.observableArrayList(PartyRepository.getInstance().findAll()));

        warehouseCombo.setButtonCell(warehouseFactory.call(null));
        warehouseCombo.setCellFactory(warehouseFactory);
        warehouseCombo.setItems(FXCollections.observableArrayList(WarehouseRepository.getInstance().findAll()));

        addressCombo.setButtonCell(addressFactory.call(null));
        addressCombo.setCellFactory(addressFactory);

        setLettersCountLabel(0);
        setParcelsCountLabel(0);
    }

    @FXML
    private void addNewLetter() {
        shipment.addItem(new Letter());
        setLettersCountLabel(++letters);
    }
}
