package Questions.TaskManagementSystem;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String email;
    private final String name;
    private final String phoneNumber;

    public User(UUID id, String email, String name, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
