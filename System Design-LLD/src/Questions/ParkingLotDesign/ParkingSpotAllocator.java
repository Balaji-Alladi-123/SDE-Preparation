package Questions.ParkingLotDesign;

import Questions.ParkingLotDesign.ParkingSpots.ParkingSpot;
import Questions.ParkingLotDesign.Vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class ParkingSpotAllocator {
    public ParkingSpot allocateSpot(Vehicle vehicle, List<ParkingFloor> parkingFloorList) throws Exception {
        for(ParkingFloor floor: parkingFloorList) {
            Optional<ParkingSpot> spot = floor.getAvailableParkingSpots(vehicle.getVechileType());
            if(spot.isPresent()) {
                return spot.get();
            }
            throw new RuntimeException("No available spot");
        }
        return null;
    }
}
