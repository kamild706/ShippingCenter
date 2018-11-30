package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Enterprise;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.EnterpriseRepository;

public class EnterpriseOverviewController {

    private App app;
    private EnterpriseRepository repository;

    @FXML
    private TableView<Enterprise> enterpriseTable;
    @FXML
    private TableColumn<Enterprise, String> nameColumn;
    @FXML
    private TableColumn<Enterprise, String> nipColumn;
    @FXML
    private TableColumn<Enterprise, String> phoneColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label nipLabel;
    @FXML
    private Label phoneLabel;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNIP()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

        enterpriseTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showEnterpriseDetails(newValue)));

        repository = EnterpriseRepository.getInstance();
        enterpriseTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    private void showEnterpriseDetails(Enterprise enterprise) {
        if (enterprise != null) {
            nameLabel.setText(enterprise.getName());
            nipLabel.setText(enterprise.getNIP());
            phoneLabel.setText(enterprise.getPhone());
        } else {
            nameLabel.setText("");
            nipLabel.setText("");
            phoneLabel.setText("");
        }
    }

    public void showAddresses() {
        Enterprise enterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (enterprise != null) {
            app.showAddressOverview(enterprise);
        }
    }

    public void updateList() {
        enterpriseTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    @FXML
    public void newEnterprise() {
        Enterprise newEnterprise = new Enterprise();
        boolean confirmed = app.showEnterpriseEditDialog(newEnterprise);
        if (confirmed) {
            repository.save(newEnterprise);
            updateList();
        }
    }

    @FXML
    public void editEnterprise() {
        Enterprise selected = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showEnterpriseEditDialog(selected);
            if (confirmed) {
                repository.update(selected);
                updateList();
                showEnterpriseDetails(selected);
            }
        }
    }

    @FXML
    public void deleteEnterprise() {
        Enterprise selected = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showEnterpriseDetails(null);
                repository.remove(selected);
                updateList();
            }
        }
    }

    @FXML
    public void setApp(App app) {
        this.app = app;
    }
}
