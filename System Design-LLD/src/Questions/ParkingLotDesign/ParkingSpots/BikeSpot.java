package Questions.ParkingLotDesign.ParkingSpots;


import Questions.ParkingLotDesign.Vehicle.VehicleType;

public  class BikeSpot extends ParkingSpot {
    public BikeSpot(String spotId) {
        super(spotId, VehicleType.BIKE);
    }
}
