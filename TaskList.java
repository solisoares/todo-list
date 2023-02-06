import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        tasks.sort(null);
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName))
                return task;
        }
        return null;
    }

    public void removeTask(String taskName) {
        Task task = getTask(taskName);
        tasks.remove(task);
    }

    public void updateTask(String taskName, String name, String description, String dueDate, Task.Status status,
            Task.Priority priority) {
        Task task = getTask(taskName);
        task.setName(name);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        task.setPriority(priority);
    }

    public void printAll() {
        ListIterator<Task> it = tasks.listIterator();
        while (it.hasNext()) {
            System.out.println(it.nextIndex() + " " + it.next().toString());
        }
    }

    private List<Task> getTasksByFilter(TaskFilter filter) {
        List<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (filter.test(task))
                filteredTasks.add(task);
        }

        return filteredTasks;
    }

    public List<Task> getTasksByCategory(String category) {
        List<Task> tasks = getTasksByFilter((task) -> task.getCategory().equals(category));
        return tasks;
    }

    public List<Task> getTasksByPriority(Task.Priority priority) {
        List<Task> tasks = getTasksByFilter((task) -> task.getPriority() == priority);
        return tasks;
    }

    public List<Task> getTasksByStatus(Task.Status status) {
        List<Task> tasks = getTasksByFilter((task) -> task.getStatus() == status);
        return tasks;
    }

    public int getNumberOfTasksByStatus(Task.Status status) {
        int numberOfTasks = 0;
        for (Task task : tasks) {
            if (task.getStatus() == status)
                numberOfTasks += 1;
        }
        return numberOfTasks;
    }

}

@FunctionalInterface
interface TaskFilter {
    public boolean test(Task task);
}