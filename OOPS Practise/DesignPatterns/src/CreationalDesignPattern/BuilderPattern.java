package CreationalDesignPattern;

class House {
    private String walls;
    private String door;
    private String windows;
    private String roof;
    private String backyard;
    private String heatingSystem;
    private String plumbing;
    private String electricity;

    // Setters (used by builder)
    public void setWalls(String walls) {
        this.walls = walls;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setBackyard(String backyard) {
        this.backyard = backyard;
    }

    public void setHeatingSystem(String heatingSystem) {
        this.heatingSystem = heatingSystem;
    }

    public void setPlumbing(String plumbing) {
        this.plumbing = plumbing;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    @Override
    public String toString() {
        return "House:" +
                "\nwalls='" + walls + '\'' +
                ", \ndoor='" + door + '\'' +
                ", \nwindows='" + windows + '\'' +
                ", \nroof='" + roof + '\'' +
                ", \nbackyard='" + backyard + '\'' +
                ", \nheatingSystem='" + heatingSystem + '\'' +
                ", \nplumbing='" + plumbing + '\'' +
                ", \nelectricity='" + electricity + '\'' +
                "\n";
    }
}

interface HouseBuilder {
    void buildWalls();

    void buildDoor();

    void buildWindows();

    void buildRoof();

    void buildBackyard();

    void buildHeatingSystem();

    void buildPlumbing();

    void buildElectricity();

    House getHouse();
}

class SimpleHouseBuilder implements HouseBuilder {
    private House house;

    public SimpleHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildWalls() {
        house.setWalls("Brick walls");
    }

    @Override
    public void buildDoor() {
        house.setDoor("wooden door");
    }

    @Override
    public void buildWindows() {
        house.setWindows("normal glass");
    }

    @Override
    public void buildRoof() {
        house.setRoof("simple roof");
    }

    @Override
    public void buildBackyard() {
        house.setBackyard("simple yard");
    }

    @Override
    public void buildHeatingSystem() {
        house.setHeatingSystem("simple");
    }

    @Override
    public void buildPlumbing() {
        house.setPlumbing("normal plumbing");
    }

    @Override
    public void buildElectricity() {
        house.setElectricity("normal wiring");
    }

    @Override
    public House getHouse() {
        return house;
    }
}


class LuxuryHouseBuilder implements HouseBuilder {
    private House house;

    public LuxuryHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildWalls() {
        house.setWalls("golden walls");
    }

    @Override
    public void buildDoor() {
        house.setDoor("hitech doors door");
    }

    @Override
    public void buildWindows() {
        house.setWindows("diamond glass");
    }

    @Override
    public void buildRoof() {
        house.setRoof("gold roof");
    }

    @Override
    public void buildBackyard() {
        house.setBackyard("highlevel yard");
    }

    @Override
    public void buildHeatingSystem() {
        house.setHeatingSystem("highlevel");
    }

    @Override
    public void buildPlumbing() {
        house.setPlumbing("highlevel plumbing");
    }

    @Override
    public void buildElectricity() {
        house.setElectricity("highlevel wiring");
    }

    @Override
    public House getHouse() {
        return house;
    }
}

class HouseDirector {
    private HouseBuilder builder;

    public HouseDirector(HouseBuilder builder) {
        this.builder = builder;
    }

    public void constructHouse() {
        builder.buildWalls();
        builder.buildDoor();
        builder.buildWindows();
        builder.buildRoof();
        builder.buildBackyard();
        builder.buildHeatingSystem();
        builder.buildPlumbing();
        builder.buildElectricity();
    }

    public House getHouse() {
        return builder.getHouse();
    }
}


class builderMain {
    public static void main(String[] args) {
        // Build a simple house
        HouseBuilder simpleBuilder = new SimpleHouseBuilder();
        HouseDirector director1 = new HouseDirector(simpleBuilder);
        director1.constructHouse();
        House simpleHouse = director1.getHouse();
        System.out.println("🏠 Simple House:\n" + simpleHouse);

        System.out.println("\n--------------------------\n");

        // Build a luxury house
        HouseBuilder luxuryBuilder = new LuxuryHouseBuilder();
        HouseDirector director2 = new HouseDirector(luxuryBuilder);
        director2.constructHouse();
        House luxuryHouse = director2.getHouse();
        System.out.println("🏰 Luxury House:\n" + luxuryHouse);
    }
}