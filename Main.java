import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskList tasks;

    public static void main(String[] args) {

        tasks = new TaskList();
        
        while (true) {
            int choice = startScreen();
            switch (choice) {
                case 0:
                    addTaskScreen();
                    break;

                case 1:
                    taskScreen();
                    break;

                case 2:
                    removeTaskScreen();
                    break;

                // case 3:
                //     updateTaskcreen();
                //     break;

                case 4:
                    tasksCountScreen();
                    break;

                case 5:
                    tasksByCategoryScreen();
                    break;

                case 6:
                    tasksByPriorityScreen();
                    break;

                case 7:
                    tasksByStatusScreen();
                    break;

                default:
                    break;
            }

        }
    }

    public static int startScreen() {
        String tasksString = getTasksString(tasks);
        int choice = -1;
        while (choice == -1) {
            clearPage();
            System.out.printf(
                      "------------------------------------\n"
                    + "*             TODO List            *\n"
                    + "------------------------------------\n"
                    + "→ Tasks:\n"
                    + "%s\n"
                    + "------------------------------------\n"
                    + "→ Choose an option:\n"
                    + "0. Add task \n"
                    + "1. Show specific task \n"
                    + "2. Remove specific task \n"
                    + "3. [NOT implemented] Update specific task \n"
                    + "4. Show tasks count \n"
                    + "5. Show tasks by Category \n"
                    + "6. Show tasks by Priority \n"
                    + "7. Show tasks by Status \n"
                    + "                     * Your Choice: ",
                    !(tasksString.isEmpty()) ? tasksString : "<No Tasks Added>");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
        }
        return choice;
    }

    private static String getTasksString(TaskList tasks) {
        String tasksString = "";
        int i = 0;
        for (Task task : tasks.getTasks()) {
            tasksString += String.format(
                    i + ". " + task.getName() + "    [Priority: " + task.getPriority() + "]    [Status: "
                            + task.getStatus() + "]    [Category: " + task.getCategory() + "]\n");
            i++;
        }
        return tasksString;
    }

    private static void addTaskScreen() {
        String name = "";
        String description = "";
        String category = "";
        String dueDate = "";
        String status = "";
        String priority = "";
        String[] taskAttr = new String[] {
                name, description, category,
                dueDate, status, priority,
        };

        String[] inputStrings = new String[] {
                "Name: ",
                "Description: ",
                "Category: ",
                "Due Date: ",
                "Status [Todo(0)/Doing(1)/Done(2)]: ",
                "Priority (0...4): "
        };

        Scanner sc = new Scanner(System.in);
        String[] lines = new String[5];
        for (int i = 0; i < lines.length; i++) {
            lines = new String[] {
                    "→ Name: " + taskAttr[0],
                    "→ Description: " + taskAttr[1],
                    "→ Category: " + taskAttr[2],
                    "→ Due Date: " + taskAttr[3],
                    "→ Status [Todo(0)/Doing(1)/Done(2)]: " + taskAttr[4],
                    "→ Priority (0...4): " + taskAttr[5],
            };
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "*                Task              *\n"
                  + "------------------------------------\n");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.printf("------------------------------------\n");
            System.out.printf(inputStrings[i]);
            taskAttr[i] = sc.nextLine();

        }

        // Add the Task
        Task task = new Task(taskAttr[0], taskAttr[1], taskAttr[2], taskAttr[3], stringToStatus(taskAttr[4]),
                stringToPriority(taskAttr[5]));
        tasks.addTask(task);
        tasks.printAll();
    }

    private static void removeTaskScreen() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Choose the task name: ");
            String taskName = sc.nextLine();
            tasks.removeTask(taskName);
            System.out.printf("The task " + "\"" + taskName + "\"" + " was removed\n");
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
    }

    // private static void updateTaskScreen() { //TODO
    // }

    private static Task.Status stringToStatus(String value) {
        Map<String, Task.Status> map = new HashMap<>();
        map.put("0", Task.Status.TODO);
        map.put("1", Task.Status.DOING);
        map.put("2", Task.Status.DONE);
        return map.get(value);
    }

    private static Task.Priority stringToPriority(String value) {
        Map<String, Task.Priority> map = new HashMap<>();
        map.put("0", Task.Priority.LOW);
        map.put("1", Task.Priority.MEDIUM_LOW);
        map.put("2", Task.Priority.MEDIUM);
        map.put("3", Task.Priority.MEDIUM_HIGH);
        map.put("4", Task.Priority.HIGH);
        return map.get(value);
    }

    private static void clearPage() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private static void taskScreen() {
        Scanner sc = new Scanner(System.in);
        String tasksString = getTasksString(tasks);
        boolean quit = false;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Tasks:\n"
                  + "%s\n",
                    tasksString);
            System.out.printf(
                    "------------------------------------\n"
                            + "→ Choose the task name: ");
            String taskName = sc.nextLine();

            clearPage();
            Task task = tasks.getTask(taskName);
            showOneTask(task);
            System.out.printf(
                    "------------------------------------\n"
                            + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
    }

    private static void showOneTask(Task task) {
        System.out.println(
                "------------------------------------\n"
              + "→ Name: " + task.getName() + "\n"
              + "→ Description: " + task.getDescription() + "\n"
              + "→ Category: " + task.getCategory() + "\n"
              + "→ Due Date: " + task.getDueDate() + "\n"
              + "→ Status: " + task.getStatus() + "\n"
              + "→ Priority: " + task.getPriority() + "\n");
    }

    private static void tasksCountScreen() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Tasks \"TODO\": %d\n"
                  + "→ Tasks \"DOING\": %d\n"
                  + "→ Tasks \"DONE\": %d\n",
                    tasks.getNumberOfTasksByStatus(Task.Status.TODO),
                    tasks.getNumberOfTasksByStatus(Task.Status.DOING),
                    tasks.getNumberOfTasksByStatus(Task.Status.DONE));
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
        // sc.close();
    }

    private static void tasksByCategoryScreen() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        String category;
        List<Task> tasksByCategory;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Choose the category: ");
            category = sc.nextLine();
            // System.out.println(category);

            clearPage();
            tasksByCategory = tasks.getTasksByCategory(category);
            // System.out
            // .println(tasksByCategory.toString() +
            // "0000000000000000000000000000000000000000000000000000000000");
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Tasks by category:\n");
            for (Task task : tasksByCategory) {
                showOneTask(task);
            }
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
    }

    private static void tasksByPriorityScreen() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        Task.Priority priority;
        List<Task> tasksByPriority;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Choose the priority from LOW to HIGH [0/1/2/3/4]: ");
            priority = stringToPriority(sc.nextLine());

            clearPage();
            tasksByPriority = tasks.getTasksByPriority(priority);
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Tasks by priority:\n");
            for (Task task : tasksByPriority) {
                showOneTask(task);
            }
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
    }

    private static void tasksByStatusScreen() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        Task.Status status;
        List<Task> tasksByStatus;
        while (!quit) {
            clearPage();
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Choose one of the status Todo, Doing and Done [0/1/2]: ");
            status = stringToStatus(sc.nextLine());

            clearPage();
            tasksByStatus = tasks.getTasksByStatus(status);
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Tasks by status:\n");
            for (Task task : tasksByStatus) {
                showOneTask(task);
            }
            System.out.printf(
                    "------------------------------------\n"
                  + "→ Quit [y/n]: ");
            quit = (sc.nextLine().equals("y") ? true : false);
        }
    }
}
