package timelogger;

import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class TaskTest {

    static Task t1;
    static Task t2;
    static Task t3;
    static Task t4;
    static Task t5;

    @BeforeClass
    public static void setUp() throws NoTaskIdException {
        t1 = new Task("1485", "This is a comment", LocalTime.of(7, 35), LocalTime.of(8, 45));
        t2 = new Task("14856", "This is a comment", LocalTime.of(8, 45), LocalTime.of(7, 30));
        t3 = new Task();
        t3.setTaskId("LT-12345");
        t3.setStartTime(LocalTime.of(8, 45));
        t3.setComment("");
        t4 = new Task("LT-1485", "This is a comment", LocalTime.of(7, 30), LocalTime.of(8, 45));
        t5 = new Task();
        t5.setStartTime(LocalTime.of(7, 30));
        t5.setEndTime(LocalTime.of(8, 45));
    }

    @Test(expected = NotExpectedTimeOrderException.class)
    public void testGetMinPerTaskNegativeDuration() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        t2.getMinPerTask();
    }

    @Test(expected = EmptyTimeFieldException.class)
    public void testGetMinPerTaskEmptyStartTime() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        t3.getMinPerTask();
    }

    @Test
    public void testGetMinPerTaskNormal() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        long expResult = 75;
        long result = t4.getMinPerTask();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValidRedmineTaskIdTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = t1.isValidRedmineTaskId();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValidRedmineTaskIdFalse() throws NoTaskIdException {
        boolean expResult = false;
        boolean result = t2.isValidRedmineTaskId();
        assertEquals(expResult, result);
    }

    @Test(expected = NoTaskIdException.class)
    public void testIsValidRedmineTaskIdNoId() throws NoTaskIdException {
        t5.isValidRedmineTaskId();
    }

    @Test
    public void testIsValidLTTaskIdTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = t4.isValidLTTaskId();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValidLTTaskIdFalse() throws NoTaskIdException {
        boolean expResult = false;
        boolean result = t3.isValidLTTaskId();
        assertEquals(expResult, result);
    }

    @Test(expected = NoTaskIdException.class)
    public void testIsValidLTTaskIdNoId() throws NoTaskIdException {
        t5.isValidLTTaskId();
    }

    @Test
    public void testIsValidTaskIDFalse() throws NoTaskIdException {
        boolean expResult = false;
        boolean result = t3.isValidTaskID();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsValidTaskIDTrue() throws NoTaskIdException {
        boolean expResult = true;
        boolean result = t1.isValidTaskID();
        assertEquals(expResult, result);
    }

    @Test(expected = NoTaskIdException.class)
    public void testIsValidTaskIdNoId() throws NoTaskIdException {
        t5.isValidTaskID();
    }

    @Test
    public void testIsMultipleQuarterHourTrue() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        boolean expResult = true;
        boolean result = t4.isMultipleQuarterHour();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsMultipleQuarterHourFalse() throws NotExpectedTimeOrderException, EmptyTimeFieldException {
        boolean expResult = false;
        boolean result = t1.isMultipleQuarterHour();
        assertEquals(expResult, result);
    }

    @Test(expected = NoTaskIdException.class)
    public void testGetTaskIdNoId() throws NoTaskIdException {
        t5.getTaskId();
    }

    @Test
    public void testGetCommentNoComment() {
        String expResult = "";
        String result = t5.getComment();
        assertEquals(expResult, result);
    }
}
