package Questions.ParkingLotDesign.Vehicle;

public abstract class Vehicle {
    private final String numberPlate;
    VehicleType vehicleType;

    public Vehicle(String numberPlate, VehicleType vehicleType) {
        this.numberPlate = numberPlate;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVechileType() {
        return vehicleType;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

}
