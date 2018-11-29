package pl.p32.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.p32.app.model.Address;
import pl.p32.app.model.Party;
import pl.p32.app.model.Person;
import pl.p32.app.model.repository.PersonRepository;
import pl.p32.app.view.AddressOverviewController;
import pl.p32.app.view.PersonEditDialogController;
import pl.p32.app.view.PersonOverviewController;
import pl.p32.app.view.RootLayoutController;

import java.io.IOException;
import java.util.List;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Centrum przesyłkowe");

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            RootLayoutController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/PersonOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            PersonOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj osobę");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showAddressOverview(Party party) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/AddressOverview.fxml"));
            AnchorPane customerOverview = loader.load();
            rootLayout.setCenter(customerOverview);

            AddressOverviewController controller = loader.getController();
            controller.setApp(this);
            controller.setParty(party);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        /*PersonRepository repository = PersonRepository.getInstance();
        List<Person> people = repository.findAll();
        Person person = people.get(0);

        Address address = new Address();
        address.setBuildingNumber("21/3");
        address.setCity("Białystok");
        address.setCountry("Polska");
        address.setStreet("Wiejska");
        address.setZipcode("15-102");
        person.addAddress(address);
        repository.update(person);*/
    }
}
