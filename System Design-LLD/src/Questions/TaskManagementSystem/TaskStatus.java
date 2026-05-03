package Questions.TaskManagementSystem;

public enum TaskStatus {
    IN_PROGRESS("In Progress"),
    TO_DO("To Do"),
    DONE("Done"),
    BLOCKED("Blocked");
    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
