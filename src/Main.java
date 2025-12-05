import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Task> tasks = new ArrayList<>();
    static int nextId = 1;

    public static void main(String[] args) {

        tasks = FileService.loadTasks();
        if (!tasks.isEmpty()) {
            nextId = tasks.get(tasks.size() - 1).getId() + 1;
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Marcar como concluída");
            System.out.println("4 - Remover tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Digite o título da tarefa: ");
                    String title = scanner.nextLine();
                    Task task = new Task(nextId++, title);
                    tasks.add(task);
                    System.out.println("✅ Tarefa criada!");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Tarefas ---");
                    if (tasks.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        for (Task t : tasks) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID da tarefa para concluir: ");
                    int idToComplete = scanner.nextInt();

                    Task found = findTaskById(idToComplete);
                    if (found != null) {
                        found.markCompleted();
                        System.out.println("✅ Tarefa concluída!");
                    } else {
                        System.out.println("❌ Tarefa não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID da tarefa para remover: ");
                    int idToRemove = scanner.nextInt();
                    tasks.removeIf(t -> t.getId() == idToRemove);
                    System.out.println("🗑️ Tarefa removida!");
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);

        scanner.close();
    }

    static Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
