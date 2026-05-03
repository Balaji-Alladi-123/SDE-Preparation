package Questions.ParkingLotDesign.ParkingSpots;

import Questions.ParkingLotDesign.Vehicle.Vehicle;
import Questions.ParkingLotDesign.Vehicle.VehicleType;

public abstract class ParkingSpot {
    private final String spotId;
    private final VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;

    ParkingSpot(String spotId, VehicleType vehicleType) {
        this.spotId = spotId;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    public synchronized boolean assignVehicleToSpot(Vehicle vehicle) {
        if(isOccupied || vehicle.getVechileType() != vehicleType) {
            System.out.println("No Spot Available for"+ vehicleType);
           return false;
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public synchronized void unAssignVehicleFromSpot(Vehicle vehicle) {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public synchronized void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

}
