public class Deadline extends Task {
    private final String time;

    Deadline(String name, TaskType type, String time) {
        super(name, type);
        this.time = time;
    }

    Deadline(String name, TaskType type, String time, boolean done) {
        super(name, type, done);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + time + ")";
    }

}
