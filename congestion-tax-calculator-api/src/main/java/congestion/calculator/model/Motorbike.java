package congestion.calculator.model;

public class Motorbike implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Motorbike";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return false;
    }
}