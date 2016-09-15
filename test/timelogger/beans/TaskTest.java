package timelogger.beans;

import java.time.LocalTime;
import timelogger.exceptions.NotExpectedTimeOrderException;
import timelogger.exceptions.InvalidTaskIdException;
import timelogger.exceptions.NoTaskIdException;
import timelogger.exceptions.EmptyTimeFieldException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {
    
    private Task getTaskWithoutComment() {
        Task task = new Task("4868", null, "10:45", "11:30");
        return task;
    }
    
    private Task getNotQuarterHourTask() {
        Task task = new Task("1485", "This is a comment", 7, 35, 8, 45);
        return task;
    }
    
    private Task getTaskWithMissingId() {
        Task task = new Task(null, "comment", 7, 30, 8, 45);
        return task;
    }
    
    private Task getTaskWithNotExpectedTimeOrder() {
        Task task = new Task("1485", "This is a comment", 8, 45, 7, 30);
        return task;
    }
    
    private Task getTaskWithEmptyTimeField() {
        Task task = new Task("LT-4894", "", null, "08:45");
        return task;
    }
    
    private Task getValidLTTask() {
        Task task = new Task("LT-4894", "comment", "07:30", "08:45");
        return task;
    }
    
    private Task getValidRedmineTask() {
        Task task = new Task("4894", "comment", "07:30", "08:45");
        return task;
    }
    
    private Task getInvalidLTTask() {
        Task task = new Task("LT-48954", "comment", "07:30", "08:45");
        return task;
    }
    
    private Task getInvalidRedmineTask() {
        Task task = new Task("44894", "comment", "07:30", "08:45");
        return task;
    }
    
    @Test(expected = NotExpectedTimeOrderException.class)
    public void testGetMinPerTaskNegativeDuration() {
        getTaskWithNotExpectedTimeOrder().getMinPerTask();
    }
    
    @Test(expected = EmptyTimeFieldException.class)
    public void testGetMinPerTaskEmptyStartTime() {
        getTaskWithEmptyTimeField().getMinPerTask();
    }
    
    @Test
    public void testGetMinPerTaskNormal() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        long expResult = 75;
        long result = getValidLTTask().getMinPerTask();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsValidRedmineTaskIdTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = getValidRedmineTask().isValidRedmineTaskId();
        assertEquals(expResult, result);
    }
    
    @Test(expected = InvalidTaskIdException.class)
    public void testIsValidRedmineTaskIdFalse() throws NoTaskIdException, InvalidTaskIdException {
        boolean expResult = false;
        boolean result = getInvalidRedmineTask().isValidRedmineTaskId();
        assertEquals(expResult, result);
    }
    
    @Test(expected = NoTaskIdException.class)
    public void testIsValidRedmineTaskIdNoId() throws NoTaskIdException {
        getTaskWithMissingId().isValidRedmineTaskId();
    }
    
    @Test
    public void testIsValidLTTaskIdTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = getValidLTTask().isValidLTTaskId();
        assertEquals(expResult, result);
    }
    
    @Test(expected = InvalidTaskIdException.class)
    public void testIsValidLTTaskIdFalse() throws NoTaskIdException, InvalidTaskIdException {
        boolean expResult = false;
        boolean result = getInvalidLTTask().isValidLTTaskId();
        assertEquals(expResult, result);
    }
    
    @Test(expected = NoTaskIdException.class)
    public void testIsValidLTTaskIdNoId() throws NoTaskIdException {
        getTaskWithMissingId().isValidLTTaskId();
    }
    
    @Test(expected = InvalidTaskIdException.class)
    public void testIsValidTaskIDFalse() throws NoTaskIdException, InvalidTaskIdException {
        boolean expResult = false;
        boolean result = getInvalidLTTask().isValidTaskID();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsValidTaskIDTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = getValidRedmineTask().isValidTaskID();
        assertEquals(expResult, result);
    }
    
    @Test(expected = NoTaskIdException.class)
    public void testIsValidTaskIdNoId() throws NoTaskIdException {
        getTaskWithMissingId().isValidTaskID();
    }
    
    @Test
    public void testIsMultipleQuarterHourTrue() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        boolean expResult = true;
        boolean result = getValidLTTask().isMultipleQuarterHour();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsMultipleQuarterHourFalse() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        boolean expResult = false;
        boolean result = getNotQuarterHourTask().isMultipleQuarterHour();
        assertEquals(expResult, result);
    }
    
    @Test(expected = NoTaskIdException.class)
    public void testGetTaskIdNoId() throws NoTaskIdException {
        getTaskWithMissingId().getTaskId();
    }
    
    @Test
    public void testGetCommentNoComment() {
        String expResult = "";
        String result = getTaskWithoutComment().getComment();
        assertEquals(expResult, result);
    }
    
    @Test(expected = InvalidTaskIdException.class)
    public void testTaskInvalidId() throws InvalidTaskIdException, NoTaskIdException {
        getInvalidRedmineTask();
    }
    
    @Test(expected = NoTaskIdException.class)
    public void testTaskNoId() throws InvalidTaskIdException, NoTaskIdException {
        getTaskWithMissingId();
    }
    
    @Test
    public void testStringToLocalTime() {
        LocalTime expResult = LocalTime.of(9, 0);
        LocalTime result = timelogger.beans.Task.stringToLocalTime("09:00");
        assertEquals(expResult, result);
    }
    
}
