import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String name;
    private String description;
    private boolean isCompleted;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }
}

public class ToDoList {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public ToDoList() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.run();
    }

    private void run() {
        int choice;
        do {
            displayMenu();
            System.out.print("Wybierz opcję (1/2/3/4/5): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskAsCompleted();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    displayTasks();
                    break;
                case 5:
                    System.out.println("Koniec programu.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        } while (choice != 5);
    }

    private void displayMenu() {
        System.out.println("1. Dodaj nowe zadanie");
        System.out.println("2. Oznacz zadanie jako zakończone");
        System.out.println("3. Usuń zadanie");
        System.out.println("4. Wyświetl listę zadań");
        System.out.println("5. Wyjście");
    }

    private void addTask() {
        System.out.print("Podaj nazwę zadania: ");
        String name = scanner.nextLine();
        System.out.print("Podaj opis zadania: ");
        String description = scanner.nextLine();

        Task task = new Task(name, description);
        tasks.add(task);

        System.out.println("Zadanie \"" + name + "\" zostało dodane do listy.");
    }

    private void markTaskAsCompleted() {
        System.out.print("Podaj numer zadania do oznaczenia jako zakończone: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsCompleted();
            System.out.println("Zadanie \"" + task.getName() + "\" zostało oznaczone jako zakończone.");
        } else {
            System.out.println("Nieprawidłowy numer zadania.");
        }
    }

    private void removeTask() {
        System.out.print("Podaj numer zadania do usunięcia: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.remove(taskNumber - 1);
            System.out.println("Zadanie \"" + task.getName() + "\" zostało usunięte z listy.");
        } else {
            System.out.println("Nieprawidłowy numer zadania.");
        }
    }

    private void displayTasks() {
        System.out.println("Lista zadań:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isCompleted() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getName() + ": " + task.getDescription());
        }

        if (tasks.isEmpty()) {
            System.out.println("(brak zadań)");
        }
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }
}
