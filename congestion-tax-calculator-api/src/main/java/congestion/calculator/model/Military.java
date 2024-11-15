package congestion.calculator.model;

public class Military implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Military";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}
