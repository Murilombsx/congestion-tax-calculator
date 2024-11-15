package congestion.calculator.factory;


import congestion.calculator.model.*;

public class VehicleFactory {

    public static Vehicle getVehicle(String vehicleModel) {
        vehicleModel = vehicleModel.toLowerCase();
        return switch (vehicleModel) {
            case "car" -> new Car();
            case "diplomat" -> new Diplomat();
            case "emergency" -> new Emergency();
            case "foreign" -> new Foreign();
            case "military" -> new Military();
            case "motorbike" -> new Motorbike();
            case "motorcycle" -> new Motorcycle();
            case "tractor" -> new Tractor();
            default -> throw new IllegalArgumentException("Invalid vehicle model: " + vehicleModel);
        };
    }
}
