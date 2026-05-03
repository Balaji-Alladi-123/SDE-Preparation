package Questions.ParkingLotDesign;

import Questions.ParkingLotDesign.ParkingSpots.BikeSpot;
import Questions.ParkingLotDesign.ParkingSpots.CarSpot;
import Questions.ParkingLotDesign.ParkingSpots.TruckSpot;
import Questions.ParkingLotDesign.Ticket.FeeCalculationStrategy;
import Questions.ParkingLotDesign.Ticket.HourlyFeeStrategy;
import Questions.ParkingLotDesign.Vehicle.*;

import java.util.Scanner;

public class ParkingLotDemo {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        FeeCalculationStrategy feeCalculationStrategy = new HourlyFeeStrategy();
        ParkingLot parkingLot = new ParkingLot(feeCalculationStrategy);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Parking Lot System ---");
            System.out.println("1. Add Parking Floor");
            System.out.println("2. Add Parking Spot");
            System.out.println("3. Park a Vehicle");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    System.out.print("Enter floor ID: ");
                    String floorId = scanner.nextLine();
                    ParkingFloor floor = new ParkingFloor(floorId);
                    parkingLot.addParkingFloor(floor);
                    System.out.println("Floor added successfully.");
                    break;
                }
                case 2: {
                    System.out.print("Enter floor ID to add spot: ");
                    String floorId = scanner.nextLine();
                    ParkingFloor floor = parkingLot.getFloorId(floorId);
                    if (floor == null) {
                        System.out.println("Floor not found!");
                        break;
                    }
                    System.out.print("Enter Spot Type (CAR/BIKE/TRUCK): ");
                    String type = scanner.nextLine().toUpperCase();
                    System.out.print("Enter Spot ID: ");
                    String spotId = scanner.nextLine();

                    switch (type) {
                        case "CAR":
                            floor.addParkingSpot(new CarSpot(spotId));
                            break;
                        case "BIKE":
                            floor.addParkingSpot(new BikeSpot(spotId));
                            break;
                        case "TRUCK":
                            floor.addParkingSpot(new TruckSpot(spotId));
                            break;
                        default:
                            System.out.println("Invalid spot type!");
                            continue;
                    }
                    System.out.println("Spot added successfully.");
                    break;
                }
                case 3: {
                    System.out.print("Enter vehicle number: ");
                    String number = scanner.nextLine();
                    System.out.print("Enter vehicle type (CAR/BIKE/TRUCK): ");
                    String type = scanner.nextLine().toUpperCase();

                    Vehicle vehicle;
                    switch (type) {
                        case "CAR":
                            vehicle = new Car(number, VehicleType.CAR);
                            break;
                        case "BIKE":
                            vehicle = new Bike(number, VehicleType.BIKE);
                            break;
                        case "TRUCK":
                            vehicle = new Truck(number, VehicleType.TRUCK);
                            break;
                        default:
                            System.out.println("Invalid vehicle type!");
                            continue;
                    }

                    Thread entryThread = new Thread(new EntryGate(vehicle, parkingLot));
                    entryThread.start();
                    entryThread.join(); // Wait for this vehicle to finish processing
                    break;
                }
                case 4: {
                    exit = true;
                    System.out.println("Exiting Parking Lot System.");
                    break;
                }
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        scanner.close();
    }
}
