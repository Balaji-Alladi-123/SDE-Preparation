package CreationalDesignPattern;

// Transport.java (Interface)
interface Transport {
    void deliver();
}

// Truck.java (Concrete Product)
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by land in a truck.");
    }
}

// Ship.java (Concrete Product)
class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by sea in a ship.");
    }
}

class Helicopter implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by helicopter");
    }
}

// Logistics.java (Creator)
abstract class Logistics {
    // Factory method
    public abstract Transport createTransport();

    // Common business logic that uses the product
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

// RoadLogistics.java (Concrete Creator)
class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

// SeaLogistics.java (Concrete Creator)
class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
class AirLogistics extends Logistics {
    public Transport createTransport() {
        return new Helicopter();
    }
}

// Main.java (Client)
public class FactoryPattern {
    public static void main(String[] args) {
        Logistics logistics;

        // Use Road Logistics
        logistics = new RoadLogistics();
        logistics.planDelivery();  // Output: Delivering by land in a truck.

        // Use Sea Logistics
        logistics = new SeaLogistics();
        logistics.planDelivery();  // Output: Delivering by sea in a ship.

        logistics = new AirLogistics();
        logistics.planDelivery();
    }
}

