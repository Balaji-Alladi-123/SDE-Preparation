package Questions.MovieTicketBookingSystem.concurrencyImp;

import Questions.MovieTicketBookingSystem.enums.SeatStatus;
import Questions.MovieTicketBookingSystem.models.Seat;
import Questions.MovieTicketBookingSystem.models.Show;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SeatLockManager {

    private final Map<Show, Map<Seat, Integer>> lockedSeats = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final long LOCK_TIMEOUT = 500; //0.5 seconds;

    public void lockSeats(Show show, List<Seat> seats, int userId) {
        synchronized (show) {
            for (Seat seat : seats) {
                if (seat.getStatus() != SeatStatus.AVAILABLE) {
                    System.out.println("No seats are available");
                    return;
                }
            }

            seats.stream().forEach(seat -> seat.setStatus(SeatStatus.LOCKED));

            lockedSeats.computeIfAbsent(show, k -> new ConcurrentHashMap<>());
            for (Seat seat : seats) {
                lockedSeats.get(show).put(seat, userId);
            }
        }
        scheduler.schedule(() -> unLockSeat(show, seats, userId), LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
        System.out.println("Locked seats: " + seats.stream().map(Seat::getId).toList() + " for user " + userId);
    }

    public void unLockSeat(Show show, List<Seat> seats, int userId) {
        synchronized (show) {
            Map<Seat, Integer> showLocks = lockedSeats.get(show);
            if (showLocks != null) {
                for (Seat seat : seats) {
                    if (showLocks.containsKey(seat) && showLocks.get(seat).equals(userId)) {
                        showLocks.remove(seat);
                        if (seat.getStatus() == SeatStatus.LOCKED) {
                            seat.setStatus(SeatStatus.AVAILABLE);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to timeout.");
                        } else {
                            showLocks.remove(seat);
                            System.out.println("Unlocked seat: " + seat.getId() + " due to booking completion.");
                        }
                    }
                }
            }
            if (showLocks.isEmpty()) {
                lockedSeats.remove(show);
            }
        }
    }

    /**
     * scheduler.awaitTermination(5, TimeUnit.SECONDS).
     * waits up to 5 seconds for tasks to finish.
     * If they finish in time → returns true.
     * If they don’t finish in time → returns false.
     * The ! (not operator) flips the result.
     * If it returns true (all tasks done) → !true = false → if block is skipped.
     * If it returns false (timeout expired, tasks still running) → !false = true → enters if block → calls shutdownNow().
     **/
    public void shutDown() {
        System.out.println("Shutting down SeatLockProvider scheduler.");
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException exception) {
            scheduler.shutdown();
            Thread.currentThread().interrupt();
        }
    }
}



