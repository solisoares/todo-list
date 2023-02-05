public class Task implements Comparable<Task> {
    enum Priority {
        LOW, MEDIUM_LOW, MEDIUM, MEDIUM_HIGH, HIGH
    }

    enum Status {
        TODO, DOING, DONE
    }

    private String name;
    private String description;
    private String category;
    private String dueDate;
    private Status status;
    private Priority priority;

    public Task(String name, String description, String category, String dueDate, Status status, Priority priority) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        if (other.priority.ordinal() == this.priority.ordinal())
            return 0;
        else if (other.priority.ordinal() > this.priority.ordinal())
            return 1;
        else
            return -1;
    }
    
    @Override
    public String toString() {
        return "Task [name=" + name + ", description=" + description + ", category=" + category + ", dueDate=" + dueDate
                + ", status=" + status + ", priority=" + priority + "]";
    }


}