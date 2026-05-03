package Questions.TaskManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskManager {
    private static TaskManager instance;
    private  final Map<UUID, Task> tasks;

    TaskManager() {
        this.tasks = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManager getInstance() {
          if(instance == null) {
              instance = new TaskManager();
          }
          return  instance;
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTaskById(UUID id) {
        if(!tasks.containsKey(id)) {
            throw new RuntimeException("No task available for this id:" + id);
        } else {
            return tasks.get(id);
        }
    }

    public void updateTaskStatus(UUID id, TaskStatus status) {
        tasks.get(id).updateTaskStatus(status);
    }

    public void updateTaskPriority(UUID taskId, TaskPriority priority) {
        getTaskById(taskId).updatePriority(priority);
    }

   public void assignUser(UUID taskId, User user) {
        getTaskById(taskId).assignUser(user);
   }
    public void addComment(UUID taskId, String commentText, User author) {
        Task task = getTaskById(taskId);
        task.addComments(new Comment(commentText, author));
    }

    public List<Task> getTasksListByUser(User user) {
       return tasks.values().stream()
                .filter(task -> user.equals(task.getAssignee()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksListByUser(TaskStatus status) {
        return tasks.values().stream()
                .filter(task -> status.equals(task.getStatus()))
                .collect(Collectors.toList());
    }
    public void deleteTask(UUID taskId) {
        tasks.remove(taskId);
    }
    public List<Task> searchTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
