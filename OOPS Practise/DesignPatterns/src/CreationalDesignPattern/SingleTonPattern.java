package CreationalDesignPattern;

class DataBaseManager {
    private static DataBaseManager singleInstance;
    private String connection;

    private DataBaseManager() {
        this.connection = "http/localhost:3000:MyDB-postgres";
        System.out.println(STR."my connections\{connection}");
    }

    // Step 3: Public static method to get the instance (lazy + thread-safe with sync block)
    public static DataBaseManager getInstance() {
        if (singleInstance == null) {
            synchronized (DataBaseManager.class) { // only one thread creates the instance in a multi-threaded environment
                if (singleInstance == null) {
                    singleInstance = new DataBaseManager();
                }
            }
        }
        return singleInstance;
    }

    public void connect() {
        System.out.println(STR."Connecting to database with: \{connection}");
    }

    public void disconnect() {
        System.out.println("Disconnecting from database.");
    }

}

public class SingleTonPattern {
    public static void main(String[] args) {
        
        DataBaseManager dataBaseManager1 = DataBaseManager.getInstance();
        DataBaseManager dataBaseManager2 = DataBaseManager.getInstance();
        dataBaseManager1.connect();
        dataBaseManager2.connect();
        dataBaseManager1.disconnect();
    }
}
