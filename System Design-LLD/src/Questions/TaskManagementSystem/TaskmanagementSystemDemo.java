package Questions.TaskManagementSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskmanagementSystemDemo {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskManager taskManager = TaskManager.getInstance();
    private final Map<UUID, User> users = new HashMap<>();

    public void run() {
        while (true) {
            System.out.println("\n===== Task Management System =====");
            System.out.println("1. Create User");
            System.out.println("2. Create Task");
            System.out.println("3. Assign Task to User");
            System.out.println("4. Update Task Status");
            System.out.println("5. List Tasks by User");
            System.out.println("6. Search Tasks by Keyword");
            System.out.println("7. Delete Task");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createUser();
                case 2 -> createTask();
                case 3 -> assignTask();
                case 4 -> updateTaskStatus();
                case 5 -> listTasksByUser();
                case 6 -> searchTasks();
                case 7 -> deleteTask();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        UUID id = UUID.randomUUID();
        User user = new User(id, name, email, phone);
        users.put(id, user);
        System.out.println("User created with ID: " + id);
    }

    private void createTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        TaskPriority priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());

        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, title, desc, LocalDateTime.now(), priority, null);
        taskManager.createTask(task);
        System.out.println("Task created with ID: " + taskId);
    }

    private void assignTask() {
        UUID taskId = getUUIDInput("Enter task ID to assign: ");
        UUID userId = getUUIDInput("Enter user ID to assign to: ");

        if (!users.containsKey(userId)) {
            System.out.println("User not found!");
            return;
        }

        taskManager.assignUser(taskId, users.get(userId));
        System.out.println("Task assigned.");
    }

    private void updateTaskStatus() {
        UUID taskId = getUUIDInput("Enter task ID: ");
        System.out.print("Enter new status (TODO, IN_PROGRESS, DONE, BLOCKED): ");
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());

        taskManager.updateTaskStatus(taskId, status);
        System.out.println("Task status updated.");
    }

    private void listTasksByUser() {
        UUID userId = getUUIDInput("Enter user ID: ");
        if (!users.containsKey(userId)) {
            System.out.println("User not found!");
            return;
        }

        List<Task> userTasks = taskManager.getTasksListByUser(users.get(userId));
        System.out.println("Tasks for " + users.get(userId).getName() + ":");
        userTasks.forEach(task -> System.out.println("- " + task.getTitle()));
    }

    private void searchTasks() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        List<Task> results = taskManager.searchTasks(keyword);
        System.out.println("Matching tasks:");
        results.forEach(task -> System.out.println("- " + task.getTitle()));
    }

    private void deleteTask() {
        UUID taskId = getUUIDInput("Enter task ID to delete: ");
        taskManager.deleteTask(taskId);
        System.out.println("Task deleted.");
    }

    private UUID getUUIDInput(String prompt) {
        System.out.print(prompt);
        return UUID.fromString(scanner.nextLine());
    }

    public static void main(String[] args) {
        new TaskmanagementSystemDemo().run();
    }
}
