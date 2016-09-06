package timelogger;

import java.time.Duration;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * With the instantiation of this class we can create tasks. We can set a tasks
 * Id, the time of its start, the time of its end, we can add a comment to
 * detail. We can check if a task Id is valid and we can ask for the duration of
 * the task.
 *
 * @author precognox
 */
@Getter
@NoArgsConstructor
public class Task {

    @Setter
    private String taskId;
    @Setter
    private LocalTime startTime;
    @Setter
    private LocalTime endTime;
    @Setter
    private String comment;
    private long minPerTask;

    /**
     * This method is a constructor with 2 String and 2 LocalTime parameters.
     *
     * @param taskId This is the Id of the task. Redmine project: 4 digits, LT
     * project: LT-(4 digits)
     * @param comment In this parameter you can add some detail about what did
     * you do exactly.
     * @param startTime You can set the time when you started the task:
     * LocalTime.of(hh,mm)
     * @param endTime You can set the time when you finished the task:
     * LocalTime.of(hh,mm)
     */
    public Task(String taskId, String comment, LocalTime startTime, LocalTime endTime) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    /**
     * This method is a getter for the minPerTask field.
     *
     * @return with the time interval between startTime and endTime in minutes
     */
    public long getMinPerTask() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    /**
     * This method checks if the Id of the task is a valid redmine task Id.
     *
     * @return true, if it is valid, false if it isn't valid.
     */
    public boolean isValidRedmineTaskId() {
        return taskId.matches("\\d{4}");
    }

    /**
     * This method checks if the Id of the task is a valid LT task Id.
     *
     * @return true, if it is valid, false if it isn't valid.
     */
    public boolean isValidLTTaskId() {
        return taskId.matches("LT-\\d{4}");
    }

    /**
     * This method checks if the Id of the task is a valid task Id (redmine or
     * LT project task id).
     *
     * @return true, if it is valid, false if it isn't valid.
     */
    public boolean isValidTaskID() {
        return isValidLTTaskId() || isValidRedmineTaskId();
    }
    
    /**
     * This method checks if the minutes are the multiple of quarter hour
     * @return true, if it is multiple, but false if it isn't.
     */
    public boolean isMultipleQuarterHour() {
        if (getMinPerTask() % 15 == 0) {
            return true;
        } 
        else {
            return false;
        }
    }

}
