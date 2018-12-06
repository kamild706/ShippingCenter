package pl.p32.app.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.p32.app.model.Courier;
import pl.p32.app.model.Party;
import pl.p32.app.model.Warehouse;
import pl.p32.app.model.repository.WarehouseRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class CourierEditDialogController implements Initializable {

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TableView<Warehouse> warehousesTableView;
    @FXML
    private ComboBox<Warehouse> warehouseCombo;
    @FXML
    private TableColumn<Warehouse, String> warehouseNameColumn;

    private Stage dialogStage;
    private Courier object;
    private boolean confirmed = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCourier(Courier object) {
        this.object = object;

        firstnameField.setText(object.getFirstname());
        lastnameField.setText(object.getLastname());

        warehousesTableView.setItems(FXCollections.observableArrayList(object.getWarehouses()));
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        object.setFirstname(firstnameField.getText());
        object.setLastname(lastnameField.getText());

        confirmed = true;
        dialogStage.close();
    }

    public void deleteWarehouse() {

    }

    public void addWarehouse() {

    }

    public void handleCancel() {
        dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        warehouseCombo.setButtonCell(warehouseFactory.call(null));
        warehouseCombo.setCellFactory(warehouseFactory);
        warehouseCombo.setItems(FXCollections.observableArrayList(WarehouseRepository.getInstance().findAll()));
    }
}
