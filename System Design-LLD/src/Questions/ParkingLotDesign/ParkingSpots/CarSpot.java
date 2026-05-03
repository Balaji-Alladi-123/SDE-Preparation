package Questions.ParkingLotDesign.ParkingSpots;

import Questions.ParkingLotDesign.Vehicle.VehicleType;

public  class CarSpot extends ParkingSpot {
    public CarSpot(String spotId) {
        super(spotId, VehicleType.CAR);
    }
}
