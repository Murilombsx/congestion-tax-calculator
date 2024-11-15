package congestion.calculator.model;

public class Motorcycle implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}

