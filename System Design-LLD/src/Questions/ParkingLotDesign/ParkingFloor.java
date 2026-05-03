package Questions.ParkingLotDesign;

import Questions.ParkingLotDesign.ParkingSpots.ParkingSpot;
import Questions.ParkingLotDesign.ParkingSpots.SpotType;
import Questions.ParkingLotDesign.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingFloor {
    private final String floorNumber;
    private final Map<VehicleType, List<ParkingSpot>> spotTypeListMap;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    ParkingFloor(String floorNumber) {
        this.floorNumber = floorNumber;
        this.spotTypeListMap = new ConcurrentHashMap<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot) throws Exception {
        lock.writeLock().lock();
        try {
            spotTypeListMap.computeIfAbsent(parkingSpot.getVehicleType(),
                    k -> new ArrayList<>()).add(parkingSpot);
        } catch (UnknownError error) {
            throw new Exception("unable to add parking spot");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<ParkingSpot> getAvailableParkingSpots(VehicleType vehicle) throws Exception {
        lock.readLock().lock();
        try {
            List<ParkingSpot> allSpots = spotTypeListMap.get(vehicle);
            return allSpots.stream().filter(ParkingSpot::isAvailable).findFirst();
        } catch (UnknownError error) {
            throw new Exception("Failed to get Parking spots");
        } finally {
            lock.readLock().unlock();
        }
    }
    public String getFloorNumber() {
        return floorNumber;
    }
}
