import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<SubTask> subTasks = new ArrayList<>();

    public Epic(int id, String name, String description, String status) {
        super(id, name, description, status);
    }

    public Epic(int id, String name, String description, String status, ArrayList<SubTask> subTasks) {
        super(id, name, description, status);
        this.subTasks = subTasks;
    }


    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void add(SubTask subTask) {
        subTasks.add(subTask);
    }
}
