package Questions.ParkingLotDesign.Ticket;

import Questions.ParkingLotDesign.ParkingSpots.ParkingSpot;
import Questions.ParkingLotDesign.Vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketService {
    public Ticket issueTicket(Vehicle vehicle, ParkingSpot spot) {
        return new Ticket(UUID.randomUUID().toString(), LocalDateTime.now(), vehicle, spot);
    }
}
