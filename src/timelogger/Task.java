/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.*;
import java.lang.*;

/**
 *
 * @author precognox
 */
public class Task {
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;
    private long minPerTask;

    public Task(String taskId,String comment, LocalTime startTime, LocalTime endTime) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
    }
    
    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }

    public long getMinPerTask() {
        return Duration.between(startTime, endTime).toMinutes();
    }
    
    public boolean isValidTaskID()
    {
        boolean valid;
    if(taskId.length()==4)
        {
        valid=taskId.matches("\\d{4}");
        }
    else if(taskId.length()==7)
        {
        valid=taskId.matches("LT-\\d{4}");
        }
    else {valid=false;}
        return valid;
    }
    
    
}
