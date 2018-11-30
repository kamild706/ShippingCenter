package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Warehouse;
import pl.p32.app.model.repository.WarehouseRepository;

public class WarehouseOverviewController {

    private App app;
    private WarehouseRepository repository;

    @FXML
    private TableView<Warehouse> warehouseTable;
    @FXML
    private TableColumn<Warehouse, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label couriersLabel;
    @FXML
    private Label shipmentsLabel;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        warehouseTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showWarehouseDetails(newValue)));

        repository = WarehouseRepository.getInstance();
        warehouseTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    public void showWarehouseDetails(Warehouse warehouse) {
        if (warehouse != null) {
            nameLabel.setText(warehouse.getName());
            couriersLabel.setText(String.valueOf(warehouse.getCouriers().size()));
            shipmentsLabel.setText(String.valueOf(warehouse.getShipments().size()));
        } else {
            nameLabel.setText("");
            couriersLabel.setText("");
            shipmentsLabel.setText("");
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void newWarehouse() {
        Warehouse instance = new Warehouse();
        boolean confirmed = app.showWarehouseEditDialog(instance);
        if (confirmed) {
            repository.save(instance);
            updateList();
        }
    }

    public void editWarehouse() {
        Warehouse instance = warehouseTable.getSelectionModel().getSelectedItem();
        if (instance != null) {
            boolean confirmed = app.showWarehouseEditDialog(instance);
            if (confirmed) {
                repository.update(instance);
                updateList();
                showWarehouseDetails(instance);
            }
        }
    }

    public void deleteWarehouse() {
        Warehouse selected = warehouseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showWarehouseDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }

    public void updateList() {
        warehouseTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }
}
