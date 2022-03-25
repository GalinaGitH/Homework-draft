
public class SubTask extends Task {
    int epicId;

    public SubTask(int id, String name, String description, String status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicID) {
        this.epicId = epicId;
    }
}
