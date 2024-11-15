package congestion.calculator.model;

public class Foreign implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Foreign";
    }

    @Override
    public boolean IsTollFreeVehicle() {
        return true;
    }
}

