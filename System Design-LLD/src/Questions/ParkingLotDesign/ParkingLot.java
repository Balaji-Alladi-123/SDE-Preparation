package Questions.ParkingLotDesign;

import Questions.ParkingLotDesign.ParkingSpots.ParkingSpot;
import Questions.ParkingLotDesign.Ticket.FeeCalculationStrategy;
import Questions.ParkingLotDesign.Ticket.Ticket;
import Questions.ParkingLotDesign.Ticket.TicketService;
import Questions.ParkingLotDesign.Vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//SingleTon
public class ParkingLot {

    private static ParkingLot instance;
    private final List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets;
    private final FeeCalculationStrategy feeStrategy;

    public ParkingLot(FeeCalculationStrategy feeStrategy) {
        this.floors = new ArrayList<>();
        this.activeTickets =new ConcurrentHashMap<>();
        this.feeStrategy = feeStrategy;
    }

    public void addParkingFloor(ParkingFloor floor) {
         floors.add(floor);
    }
    public static synchronized ParkingLot getInstance(FeeCalculationStrategy strategy) {
        if (instance == null) instance = new ParkingLot(strategy);
        return instance;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) throws Exception {
        ParkingSpotAllocator parkingSpotAllocator = new ParkingSpotAllocator();
        ParkingSpot parkingSpot = parkingSpotAllocator.allocateSpot(vehicle,floors);
        parkingSpot.assignVehicleToSpot(vehicle);
        Ticket ticket = new TicketService().issueTicket(vehicle, parkingSpot);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public synchronized double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) throw new RuntimeException("Invalid ticket ID");
        LocalDateTime outTime = LocalDateTime.now();
        double fee = feeStrategy.calculateFee(ticket.getEntryTime(), outTime);
        ticket.getSpot().removeVehicle();
        ticket.markPaid();
        return fee;
    }

    public ParkingFloor getFloorId(String id) {
        for (ParkingFloor floor : floors) {
            if (Objects.equals(floor.getFloorNumber(), id)) {
                return floor;
            }
        }
        return null;
    }
}
