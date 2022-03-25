public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task newTask = new Task(0, "задача1", "вывести на печать", "NEW");
        final Task task = manager.saveTask(newTask);
        final Task taskOne = manager.getTaskById(task.getId());
        if (!task.equals(taskOne)) {
            System.out.println("Ошибка: задача не находится по Id " + task.getId());
        }
        Task nextTask = new Task(0, "задача2", "удаление задач", "NEW");
        manager.saveTask(nextTask);
        System.out.println(manager.getListOfTasks());
        manager.removeTaskById(newTask.getId());
        nextTask = new Task(2, "задача2", "удаление задач и написание новых", "NEW");
        manager.updateTask(nextTask);
        System.out.println(manager.getListOfTasks());

        Epic newEpic = new Epic(0, "Эпик1", "сложная задача", "NEW");
        manager.saveEpic(newEpic);
        Epic nextEpic = new Epic(0, "Эпик2", "выполнить проект", "NEW");
        manager.saveEpic(nextEpic);

        SubTask subTaskOne = new SubTask(0, "Подзадача1", "создать классы", "IN_PROGRESS", 3);
        manager.saveSubTask(subTaskOne);
        SubTask subTaskTwo = new SubTask(0, "Подзадача2", "создать методы", "NEW", 3);
        manager.saveSubTask(subTaskTwo);

        System.out.println(manager.getListOfSubTask());
        System.out.println(manager.getListOfSubtasksOfEpic(newEpic));
        manager.updateSubTask(subTaskOne);
        manager.updateEpic(newEpic);
        System.out.println(manager.getListOfEpics());
        System.out.println(manager.getEpicById(4));
        System.out.println(manager.getSubTaskById(6));
        manager.removeSubTaskById(6);
        manager.removeEpicById(3);
        System.out.println(manager.getListOfEpics());
        System.out.println(manager.getListOfSubTask());
        manager.deleteAllTasks();
        manager.deleteAllSubTasks();
        manager.deleteAllEpics();
    }
}