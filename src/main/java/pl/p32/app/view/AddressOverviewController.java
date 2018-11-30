package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Address;
import pl.p32.app.model.Enterprise;
import pl.p32.app.model.Party;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.EnterpriseRepository;
import pl.p32.app.model.repository.PersonRepository;
import pl.p32.app.model.repository.RepositoryInterface;

public class AddressOverviewController {

    private App app;
    private RepositoryInterface repository;
    private Party party;

    @FXML
    private TableView<Address> addressTable;
    @FXML
    private TableColumn<Address, String> nameColumn;

    @FXML
    private Label countryLabel;
    @FXML
    private Label zipcodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label buildingNumberLabel;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));

        addressTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showAddressDetails(newValue)));
    }

    public void showAddressDetails(Address address) {
        if (address != null) {
            countryLabel.setText(address.getCountry());
            zipcodeLabel.setText(address.getZipcode());
            cityLabel.setText(address.getCity());
            streetLabel.setText(address.getStreet());
            buildingNumberLabel.setText(address.getBuildingNumber());
        } else {
            countryLabel.setText("");
            zipcodeLabel.setText("");
            cityLabel.setText("");
            streetLabel.setText("");
            buildingNumberLabel.setText("");
        }
    }

    public void setParty(Party party) {
        this.party = party;
        addressTable.setItems(FXCollections.observableArrayList(party.getAddresses()));

        if (party instanceof Person) {
            repository = PersonRepository.getInstance();
        } else if (party instanceof Enterprise) {
            repository = EnterpriseRepository.getInstance();
        }
    }

    private void updateList() {
        addressTable.setItems(FXCollections.observableArrayList(party.getAddresses()));
    }

    public void newAddress() {
        Address newAddress = new Address();
        boolean confirmed = app.showAddressEditDialog(newAddress);
        if (confirmed) {
            party.addAddress(newAddress);
            repository.update(party);
            updateList();
        }
    }

    public void editAddress() {
        Address selected = addressTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showAddressEditDialog(selected);
            if (confirmed) {
                repository.update(party);
                updateList();
                showAddressDetails(selected);
            }
        }
    }

    public void deleteAddress() {
        Address selected = addressTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showAddressDetails(null);
                party.removeAddress(selected);
                repository.update(party);
                updateList();
            }
        }
    }

    @FXML
    public void setApp(App app) {
        this.app = app;
    }
}
