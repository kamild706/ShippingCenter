package pl.p32.app.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.p32.app.model.Address;

public class AddressEditDialogController {

    @FXML
    private TextField countryField;
    @FXML
    private TextField zipcodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField buildingNumberField;

    private Stage dialogStage;
    private Address address;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAddress(Address address) {
        this.address = address;

        countryField.setText(address.getCountry());
        zipcodeField.setText(address.getZipcode());
        cityField.setText(address.getCity());
        streetField.setText(address.getStreet());
        buildingNumberField.setText(address.getBuildingNumber());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        address.setCountry(countryField.getText());
        address.setZipcode(zipcodeField.getText());
        address.setCity(cityField.getText());
        address.setStreet(streetField.getText());
        address.setBuildingNumber(buildingNumberField.getText());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }
}
