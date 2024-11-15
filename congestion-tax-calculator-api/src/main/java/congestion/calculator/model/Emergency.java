package congestion.calculator.model;

public class Emergency implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Emergency";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}
