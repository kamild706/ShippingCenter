package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Address;
import pl.p32.app.model.Complaint;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.ComplaintRepository;
import pl.p32.app.model.repository.PersonRepository;

public class ComplaintOverviewController {

    private App app;
    private ComplaintRepository repository;

    @FXML
    private TableView<Complaint> complaintTable;
    @FXML
    private TableColumn<Complaint, String> nameColumn;

    @FXML
    private TableColumn<Complaint, String> dateColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label commentsLabel;
    @FXML
    private Label shipmentLabel;


    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreator().getName()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedDate()));

        repository = ComplaintRepository.getInstance();
        complaintTable.setItems(FXCollections.observableArrayList(repository.findAll()));

        complaintTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showComplaintDetails(newValue)));
    }

    private void showComplaintDetails(Complaint complaint) {
        if (complaint != null) {
            nameLabel.setText(complaint.getCreator().getName());
            dateLabel.setText(complaint.getFormattedDateTime());
            commentsLabel.setText(complaint.getComments());
            shipmentLabel.setText(complaint.getShipment().getName());
        } else {
            nameLabel.setText("");
            dateLabel.setText("");
            commentsLabel.setText("");
        }
    }

    public void updateList() {
        complaintTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    @FXML
    public void deleteComplaint() {
        Complaint selected = complaintTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showComplaintDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }
}
