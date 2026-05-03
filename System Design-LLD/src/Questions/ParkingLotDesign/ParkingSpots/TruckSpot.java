package Questions.ParkingLotDesign.ParkingSpots;

import Questions.ParkingLotDesign.Vehicle.VehicleType;

public class TruckSpot extends ParkingSpot {
    public TruckSpot(String spotId) {
        super(spotId, VehicleType.TRUCK);
    }
}