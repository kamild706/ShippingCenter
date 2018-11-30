package pl.p32.app.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.p32.app.model.Enterprise;

public class EnterpriseEditDialogController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField nipField;

    @FXML
    private TextField phoneField;


    private Stage dialogStage;
    private Enterprise enterprise;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;

        nameField.setText(enterprise.getName());
        nipField.setText(enterprise.getNIP());
        phoneField.setText(enterprise.getPhone());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        enterprise.setName(nameField.getText());
        enterprise.setNIP(nipField.getText());
        enterprise.setPhone(phoneField.getText());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }
}
