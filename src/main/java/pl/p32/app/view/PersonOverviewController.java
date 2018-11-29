package pl.p32.app.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.p32.app.App;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.PersonRepository;

public class PersonOverviewController {

    private App app;
    private PersonRepository repository;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstnameColumn;
    @FXML
    private TableColumn<Person, String> lastnameColumn;
    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label phoneLabel;

    @FXML
    public void initialize() {
        firstnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

        personTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPersonDetails(newValue)));

        repository = PersonRepository.getInstance();
        personTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstnameLabel.setText(person.getFirstname());
            lastnameLabel.setText(person.getLastname());
            phoneLabel.setText(person.getPhone());
        } else {
            firstnameLabel.setText("");
            lastnameLabel.setText("");
            phoneLabel.setText("");
        }
    }

    public void showAddresses() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (person != null) {
            app.showAddressOverview(person);
        }
    }

    public void updateList() {
        personTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    @FXML
    public void newPerson() {
        Person newPerson = new Person();
        boolean confirmed = app.showPersonEditDialog(newPerson);
        if (confirmed) {
            repository.save(newPerson);
            updateList();
        }
    }

    @FXML
    public void editPerson() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean confirmed = app.showPersonEditDialog(selected);
            if (confirmed) {
                repository.update(selected);
                updateList();
                showPersonDetails(selected);
            }
        }
    }

    @FXML
    public void deletePerson() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy napewno usunąć?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                showPersonDetails(null);
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
