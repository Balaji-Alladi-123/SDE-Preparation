package Questions.ParkingLotDesign;

import Questions.ParkingLotDesign.Ticket.Ticket;
import Questions.ParkingLotDesign.Vehicle.Vehicle;

// Entry Gate Simulation
public class EntryGate implements Runnable {
    private final Vehicle vehicle;
    private final ParkingLot lot;

    public EntryGate(Vehicle vehicle, ParkingLot lot) {
        this.vehicle = vehicle;
        this.lot = lot;
    }

    @Override
    public void run() {
        try {
            Ticket ticket = lot.parkVehicle(vehicle);
            System.out.println("Vehicle " + vehicle.getNumberPlate() + " parked with ticket: " + ticket.getTicketId());
        } catch (Exception e) {
            System.out.println("Parking failed for " + vehicle.getNumberPlate() + ": " + e.getMessage());
        }
    }
}
