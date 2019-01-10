package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class CourierEditDialogController {

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
    private boolean warehousesChanged = false;
    private Set<Warehouse> warehouseSet;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCourier(Courier object) {
        this.object = object;

        firstnameField.setText(object.getFirstname());
        lastnameField.setText(object.getLastname());
        warehouseSet = new HashSet<>(object.getWarehouses());

        initialize();
        warehousesTableView.setItems(FXCollections.observableArrayList(warehouseSet));
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void handleOk() {
        object.setFirstname(firstnameField.getText());
        object.setLastname(lastnameField.getText());

        if (warehousesChanged)
            object.replaceWarehouses(warehouseSet);

        confirmed = true;
        dialogStage.close();
    }

    public void deleteWarehouse() {
        warehousesChanged = true;
        Warehouse warehouse = warehousesTableView.getSelectionModel().getSelectedItem();
        warehouseSet.remove(warehouse);
        warehousesTableView.setItems(FXCollections.observableArrayList(warehouseSet));
    }

    public void addWarehouse() {
        warehousesChanged = true;
        warehouseSet.add(warehouseCombo.getValue());
        warehousesTableView.setItems(FXCollections.observableArrayList(warehouseSet));
    }

    public void handleCancel() {
        dialogStage.close();
    }

    public void initialize() {
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

        warehouseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    }
}
