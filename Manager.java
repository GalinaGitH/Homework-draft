import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int idCounter = 0;

    /**
     * Получение списка всех задач.
     */
    public ArrayList<Task> getListOfTasks() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * Получение списка всех эпиков.
     */
    public ArrayList<Epic> getListOfEpics() {
        return new ArrayList<>(epics.values());
    }

    /**
     * Получение списка всех подзадач.
     */
    public ArrayList<SubTask> getListOfSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    /**
     * Удаление всех задач.
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Удаление всех эпиков.
     */
    public void deleteAllEpics() {
        subTasks.clear();
        epics.clear();
    }

    /**
     * Удаление всех подзадач.
     */
    public void deleteAllSubTasks() {
        subTasks.clear();
    }

    /**
     * Получение задачи по идентификатору.
     */
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    /**
     * Получение эпика по идентификатору.
     */
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    /**
     * Получение подзадачи по идентификатору.
     */
    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    /**
     * Создание задачи.
     * Сам объект должен передаваться в качестве одного параметра.
     */
    public Task saveTask(Task task) {
        task.setId(++idCounter);
        tasks.put(task.getId(), task);
        return task;
    }

    /**
     * Создание эпика.
     * Сам объект должен передаваться в качестве одного параметра.
     */
    public Epic saveEpic(Epic epic) {
        epic.setId(++idCounter);
        epics.put(epic.getId(), epic);
        return epic;
    }

    /**
     * Создание подзадачи.
     * Сам объект должен передаваться в качестве одного параметра.
     */
    public SubTask saveSubTask(SubTask subTask) {
        subTask.setId(++idCounter);
        Epic currentEpic = epics.get(subTask.getEpicId());
        currentEpic.add(subTask);
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }

    /**
     * Обновление.
     * Новая версия задачи с верным идентификатором передаются в виде параметра.
     */
    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    /**
     * Обновление.
     * Новая версия эпика с верным идентификатором передаются в виде параметра.
     */
    public void updateEpic(Epic epic) {
        Epic currentEpic = epics.get(epic.getId());
        currentEpic.setName(epic.getName());
        currentEpic.setDescription(epic.getDescription());
    }

    /**
     * Обновление.
     * Новая версия подзадачи с верным идентификатором передаются в виде параметра.
     * При обновлении подзадач обновляем статус Эпика.
     */
    public void updateSubTask(SubTask subTask) {
        SubTask currentSubTask = subTasks.get(subTask.getId());
        currentSubTask.setName(subTask.getName());
        currentSubTask.setDescription(subTask.getDescription());
        currentSubTask.setStatus(subTask.getStatus());
        updateStatus(subTask.getEpicId());
    }

    /**
     * Удаление задачи по идентификатору.
     */
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    /**
     * Удаление эпика по идентификатору.
     * При удалении эпика удаляем и его подзадачи.
     */
    public void removeEpicById(int id) {
        Epic currentEpic = epics.get(id);
        ArrayList<SubTask> currentSubTasks = currentEpic.getSubTasks();
        for (SubTask subTaskOne : currentSubTasks) {
            subTasks.remove(subTaskOne.getId());
        }
        epics.remove(id);
    }

    /**
     * Удаление подзадачи по идентификатору.
     */
    public void removeSubTaskById(int id) {
        subTasks.remove(id);
    }

    /**
     * Получение списка всех подзадач определённого эпика.
     */
    public ArrayList<SubTask> getListOfSubtasksOfEpic(Epic epic) {
        Epic currentEpic = epics.get(epic.getId());
        return currentEpic.getSubTasks();
    }

    /**
     * Управление статусами эпика:
     * если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
     * если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
     * во всех остальных случаях статус должен быть IN_PROGRESS.
     */
    private String updateStatus(int id) {
        Epic epic = epics.get(id);
        ArrayList<SubTask> subTasks = epic.getSubTasks();
        if (subTasks.isEmpty()) {
            return epic.setStatus(Status.NEW);
        }
        int newCounter = 0;
        int doneCounter = 0;

        for (SubTask subTaskOne : subTasks) {

            if (subTaskOne.getStatus().equals(Status.NEW)) {
                newCounter++;
            } else if (subTaskOne.getStatus().equals(Status.DONE)) {
                doneCounter++;
            }
        }
        if (newCounter == subTasks.size()) {
            return epic.setStatus(Status.NEW);
        } else if (doneCounter == subTasks.size()) {
            return epic.setStatus(Status.DONE);
        }

        return epic.setStatus(Status.IN_PROGRESS);
    }
}






