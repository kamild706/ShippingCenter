package pl.p32.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.p32.app.model.*;
import pl.p32.app.model.repository.*;
import pl.p32.app.view.*;

import java.io.IOException;
import java.util.List;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Centrum przesyłkowe");

//        PersonRepository.getInstance();
        initRootLayout();
        showPersonOverview();
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

    public boolean showAddressEditDialog(Address address) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/AddressEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj adres");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddressEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAddress(address);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showEnterpriseOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/EnterpriseOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            EnterpriseOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showEnterpriseEditDialog(Enterprise enterprise) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/EnterpriseEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj firmę");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EnterpriseEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEnterprise(enterprise);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showComplaintOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/ComplaintOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            ComplaintOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showShipmentOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/ShipmentOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            ShipmentOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showNewShipmentDialog(Shipment shipment) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/ShipmentNewDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj przesyłkę");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ShipmentNewDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setShipment(shipment);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showWarehouseOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/WarehouseOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            WarehouseOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showWarehouseEditDialog(Warehouse warehouse) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/WarehouseEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj magazyn");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            WarehouseEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setWarehouse(warehouse);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showCourierOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/CourierOverview.fxml"));
            AnchorPane bankOverview = loader.load();
            rootLayout.setCenter(bankOverview);

            CourierOverviewController controller = loader.getController();
            controller.setApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showCourierEditDialog(Courier courier) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/CourierEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj kuriera");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CourierEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCourier(courier);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
//        WarehouseRepository repo = WarehouseRepository.getInstance();
//        Warehouse warehouse = repo.findAll().get(0);
//        CourierRepository repo1 = CourierRepository.getInstance();
//        Courier courier = repo1.findAll().get(0);
        /*courier.addWarehouse(warehouse);
        repo1.update(courier);*/
//        warehouse.addCourier(courier);
//        repo1.update(courier);
//        repo.update(warehouse);
        /*ShipmentRepository repository = ShipmentRepository.getInstance();
        List<Shipment> shipments = repository.findAll();
        repository.remove(shipments.get(0));*/
    }

    public boolean showShipmentDeliveryDialog(Shipment shipment) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/ShipmentDeliveryDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Zapisz dostawę");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ShipmentDeliveryDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setShipment(shipment);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isConfirmed();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
