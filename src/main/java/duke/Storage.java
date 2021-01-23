package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read the data of tasks from previous session's file as TaskList.
     * If there's no file yet, create an empty file, and return empty list.
     * @return the list of tasks from previous session
     */
    public TaskList readFile() {
        List<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.createNewFile()) {
                File myObj = new File(filePath);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String[] data = myReader.nextLine().split(" \\| ");
                    if (data.length == 0) {
                        break;
                    }
                    switch (data[0]) {
                        case "TODO":
                            taskList.add(new ToDo(data[2], TaskType.TODO,
                                    data[1].equals("1")));
                            break;
                        case "DEADLINE":
                            taskList.add(new Deadline(data[2], TaskType.DEADLINE,
                                    LocalDate.parse(data[3]), data[1].equals("1")));
                            break;
                        case "EVENT":
                            taskList.add(new Event(data[2], TaskType.EVENT,
                                    LocalDate.parse(data[3]), data[1].equals("1")));
                            break;
                        default:
                            return new TaskList(taskList);
                    }

                }
                myReader.close();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("(Your tasks from previous session has been successfully loaded.)");
        return new TaskList(taskList);
    }

    /**
     * Update the file with newest task data, write the updated tasks into the file.
     * @param taskList the updated task list
     */
    public void updateFile(TaskList taskList) {
        String updatedString = "";
        for (Task t : taskList.getTaskList()) {
            updatedString += t.getType();
            updatedString += " | ";
            updatedString += (t.getDone() ? 1 : 0);
            updatedString += " | ";
            updatedString += t.getName();
            if (t.getType().equals(TaskType.DEADLINE)) {
                updatedString += " | ";
                updatedString += ((Deadline) t).getTime().toString();
            } else if (t.getType().equals(TaskType.EVENT)) {
                updatedString += " | ";
                updatedString += ((Event) t).getTime().toString();
            }
            updatedString += "\n";
        }
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(updatedString);
            file.close();
            System.out.println("(Your tasks are successfully saved to the file.)\n");
        } catch (IOException e) {
            System.out.println("(Your tasks fail to saved to the file.)\n");
        }

    }
}
