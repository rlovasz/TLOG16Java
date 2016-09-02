/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author precognox
 */
public class TaskTest {
    
    public TaskTest() {
    }
    Task t1;
    Task t2;
    Task t3;
    Task t4;
    @Before
    public void setUp() {
        t1=new Task("1485","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
        t2=new Task("14856","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
        t3=new Task("LT-14856","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
        t4=new Task("LT-1485","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
    }
    
    /**
     * Test of getTaskId method, of class Task.
     */
    @Test
    public void testGetTaskId() {
        String expResult = "1485";
        String result = t1.getTaskId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartTime method, of class Task.
     */
    @Test
    public void testGetStartTime() {
        LocalTime expResult = LocalTime.of(7, 30);
        LocalTime result = t1.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndTime method, of class Task.
     */
    @Test
    public void testGetEndTime() {
        LocalTime expResult = LocalTime.of(8,45);
        LocalTime result = t1.getEndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComment method, of class Task.
     */
    @Test
    public void testGetComment() {
        String expResult = "This is a comment";
        String result = t1.getComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinPerTask method, of class Task.
     */
    @Test
    public void testGetMinPerTask() {
        long expResult = 75;
        long result = t1.getMinPerTask();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidTaskID method, of class Task.
     */
    @Test
    public void testIsValidTaskIDFalse() {
        boolean expResult = false;
        boolean result = t2.isValidTaskID();
        assertEquals(expResult, result);
    }
    @Test
    public void testIsValidTaskIDTrue() {
        boolean expResult = true;
        boolean result = t1.isValidTaskID();
        assertEquals(expResult, result);
    }
    @Test
    public void testIsValidTaskIDFalseLT() {
        boolean expResult = false;
        boolean result = t3.isValidTaskID();
        assertEquals(expResult, result);
    }
    @Test
    public void testIsValidTaskIDTrueLT() {
        boolean expResult = true;
        boolean result = t4.isValidTaskID();
        assertEquals(expResult, result);
    }
    
}
