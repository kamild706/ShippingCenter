package pl.p32.app.view;

import pl.p32.app.App;

public class RootLayoutController {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }
    
    public void showPerson() {
        app.showPersonOverview();
    }

    public void showEnterprise() {
        app.showEnterpriseOverview();
    }

    public void showComplaint() {
        app.showComplaintOverview();
    }

    public void showShipment() {
        app.showShipmentOverview();
    }

    public void showWarehouse() {
        app.showWarehouseOverview();
    }

    public void showCourier() {
        app.showCourierOverview();
    }
}
