package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Address;
import pl.p32.app.model.Complaint;
import pl.p32.app.model.Courier;
import pl.p32.app.model.repository.ComplaintRepository;
import pl.p32.app.model.repository.CourierRepository;

public class CourierOverviewController {

    private App app;
    private CourierRepository repository;

    @FXML
    private TableView<Courier> courierTableView;

    @FXML
    private TableColumn<Courier, String> firstnameColumn;

    @FXML
    private TableColumn<Courier, String> lastnameColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label warehousesLabel;


    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void initialize() {
        firstnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));

        courierTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showCourierDetails(newValue)));

        repository = CourierRepository.getInstance();
        courierTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    private void showCourierDetails(Courier courier) {
        if (courier != null) {
            firstnameLabel.setText(courier.getFirstname());
            lastnameLabel.setText(courier.getLastname());
            warehousesLabel.setText(courier.getWarehousesNames());

        } else {
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            warehousesLabel.setText("");
        }
    }

    private void updateList() {
        courierTableView.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    @FXML
    public void deleteCourier() {
        Courier selected = courierTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showCourierDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }

    @FXML
    public void newCourier() {
        Courier object = new Courier();
        boolean confirmed = app.showCourierEditDialog(object);
        if (confirmed) {
            repository.save(object);
            updateList();
        }
    }

    @FXML
    public void editCourier() {
        Courier selected = courierTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showCourierEditDialog(selected);
            if (confirmed) {
                repository.update(selected);
                updateList();
                showCourierDetails(selected);
            }
        }
    }
}
