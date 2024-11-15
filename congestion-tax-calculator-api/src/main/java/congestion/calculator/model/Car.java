package congestion.calculator.model;

public class Car implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return false;
    }
}
