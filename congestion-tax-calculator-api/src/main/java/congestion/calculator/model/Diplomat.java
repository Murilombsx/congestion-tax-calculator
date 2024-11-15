package congestion.calculator.model;

public class Diplomat implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Diplomat";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}
