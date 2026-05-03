package Questions.TaskManagementSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    private final UUID id;
    private String title;
    private  String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private TaskPriority priority;
    private User assignee;
    private final List<Comment> commentList;
    private final List<Task> taskList;

    public Task(UUID id, String title, String description,LocalDateTime localDateTime, TaskPriority priority, User user) {
       this.id = id;
       this.title = title;
       this.description = description;
       this.dueDate = localDateTime;
       this.priority = priority;
       this.assignee = user;
       this.commentList = new ArrayList<>();
       this.taskList = new ArrayList<>();
    }
    public void assignUser(User user) {
       this.assignee = user;
    }

    public void addTasks(Task task) {
        taskList.add(task);
    }

    public synchronized void updateTaskStatus(TaskStatus status) {
         this.status = status;
    }
    public synchronized void updatePriority(TaskPriority priority) {
          this.priority = priority;
    }
    public synchronized  void addComments(Comment comment) {
        commentList.add(comment);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public User getAssignee() {
        return assignee;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
