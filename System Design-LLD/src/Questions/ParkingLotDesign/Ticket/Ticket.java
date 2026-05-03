package Questions.ParkingLotDesign.Ticket;

import Questions.ParkingLotDesign.ParkingSpots.ParkingSpot;
import Questions.ParkingLotDesign.Vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
        private final String ticketId;
        private final LocalDateTime entryTime;
        private final Vehicle vehicle;
        private final ParkingSpot spot;
        private TicketStatus status;

        public Ticket(String ticketId, LocalDateTime entryTime, Vehicle vehicle, ParkingSpot spot) {
            this.ticketId = ticketId;
            this.entryTime = entryTime;
            this.vehicle = vehicle;
            this.spot = spot;
            this.status = TicketStatus.ACTIVE;
        }

        public LocalDateTime getEntryTime() {
            return entryTime;
        }

        public ParkingSpot getSpot() {
            return spot;
        }

        public TicketStatus getStatus() {
            return status;
        }

        public void markPaid() {
            this.status = TicketStatus.PAID;
        }

        public String getTicketId() {
            return ticketId;
        }
}
