package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.Complaint;
import pl.p32.app.model.Party;
import pl.p32.app.model.Person;
import pl.p32.app.model.Shipment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintNewDialogController {

    @FXML
    private TextArea commentsField;

    @FXML
    private ComboBox<Party> creator;

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
        Complaint complaint = new Complaint();
        complaint.setShipment(shipment);
        complaint.setComments(commentsField.getText());
        complaint.setCreator(creator.getValue());
        complaint.setReceivingDate(LocalDateTime.now());

        confirmed = true;
        dialogStage.close();
    }

    public void handleCancel() {
        dialogStage.close();
    }

    private void initialize() {
        Callback<ListView<Party>, ListCell<Party>> creatorFactory = new Callback<ListView<Party>, ListCell<Party>>() {
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

        creator.setButtonCell(creatorFactory.call(null));
        creator.setCellFactory(creatorFactory);
        List<Party> parties = new ArrayList<>();
        parties.add(shipment.getReceiver());
        parties.add(shipment.getSender());
        creator.setItems(FXCollections.observableArrayList(parties));
    }
}
