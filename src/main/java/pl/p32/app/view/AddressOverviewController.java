package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.p32.app.App;
import pl.p32.app.model.Address;
import pl.p32.app.model.Enterprise;
import pl.p32.app.model.Party;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.AbstractRepository;
import pl.p32.app.model.repository.EnterpriseRepository;
import pl.p32.app.model.repository.PersonRepository;

public class AddressOverviewController {

    private App app;
    private AbstractRepository repository;
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

    public void newAddress() {

    }

    public void editAddress() {}

    public void deleteAddress() {}

    @FXML
    public void setApp(App app) {
        this.app = app;
    }
}
