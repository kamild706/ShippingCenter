package pl.p32.app.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.p32.app.model.Person;

public class ComplaintNewDialog {

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField phoneField;

    private Stage dialogStage;
    private Person person;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstnameField.setText(person.getFirstname());
        lastnameField.setText(person.getLastname());
        phoneField.setText(person.getPhone());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        person.setFirstname(firstnameField.getText());
        person.setLastname(lastnameField.getText());
        person.setPhone(phoneField.getText());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }
}
