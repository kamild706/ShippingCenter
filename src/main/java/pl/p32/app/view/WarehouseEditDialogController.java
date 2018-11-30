package pl.p32.app.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.p32.app.model.Warehouse;

public class WarehouseEditDialogController {

    @FXML
    private TextField nameField;
    
    private Stage dialogStage;
    private Warehouse warehouse;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;

        nameField.setText(warehouse.getName());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        warehouse.setName(nameField.getText());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }
}
