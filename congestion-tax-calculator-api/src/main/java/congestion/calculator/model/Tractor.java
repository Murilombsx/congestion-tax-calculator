package congestion.calculator.model;

public class Tractor implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Tractor";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}
