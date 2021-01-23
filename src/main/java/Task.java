public class Task {
    protected final String name;
    protected final TaskType type;
    protected boolean done;

    Task(String name, TaskType type) {
        this.name = name;
        this.done = false;
        this.type = type;
    }

    Task(String name, TaskType type, boolean done) {
        this.name = name;
        this.done = done;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public TaskType getType() {
        return type;
    }

    public void markDone() {
        done = true;
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public String toString() {
        String returnString = "[" + type.getType() + "][" + (done ? "X" : " ") + "] ";
        return returnString + name;
    }
}
