/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author precognox
 */
public class WorkDayTest {
    
    public WorkDayTest() {
    }
    Task t1;
    Task t2;
    
    WorkDay wd1;
    WorkDay wd2;
    WorkDay wd3;
    WorkDay wd4;
    WorkDay wd5;
    WorkDay wd6;
    WorkDay wd7;
    @Before
    public void setUp() {
        wd1 = new WorkDay(420);
        wd2 = new WorkDay();
        wd3 = new WorkDay(420,LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd4 = new WorkDay(LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd5 = new WorkDay(75,LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd6 = new WorkDay(60);
        wd7 = new WorkDay(LocalDate.of(2016, Month.AUGUST, 28));
        t1 = new Task("1856","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
        t2 = new Task("1486","This is a comment",LocalTime.of(8, 45), LocalTime.of(9, 45));
    }
    
    /**
     * Test of getRequiredMinPerDay method, of class WorkDay.
     */
    @Test
    public void testGetRequiredMinPerDay1() {
        long expResult = 420;
        long result = wd1.getRequiredMinPerDay();
        assertEquals(expResult, result);
    }
     @Test
    public void testGetRequiredMinPerDay2() {
        long expResult = 450;
        long result = wd2.getRequiredMinPerDay();
        assertEquals(expResult, result);
    }
     @Test
    public void testGetRequiredMinPerDay3() {
        long expResult = 420;
        long result = wd3.getRequiredMinPerDay();
        assertEquals(expResult, result);
    }
     @Test
    public void testGetRequiredMinPerDay4() {
        long expResult = 450;
        long result = wd4.getRequiredMinPerDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExtraMinPerDay method, of class WorkDay.
     */
    @Test
    public void testGetExtraMinPerDay1() { 
        wd5.addTask(t1);
        long expResult = 0;
        long result = wd5.getExtraMinPerDay();
        assertEquals(expResult, result);
    }
    @Test 
    public void testGetExtraMinPerDay2() {
        wd4.addTask(t1);
        long expResult = -375;
        long result = wd4.getExtraMinPerDay();
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testGetExtraMinPerDay3() {
        wd6.addTask(t1);
        long expResult = 15;
        long result = wd6.getExtraMinPerDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSumPerDay method, of class WorkDay.
     */
    @Test
    public void testGetSumPerDay() {
        wd6.addTask(t1);
        wd6.addTask(t2);
        long expResult = 135;
        long result = wd6.getSumPerDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of addTask method, of class WorkDay.
     */
    @Test
    public void testAddTask() {
        wd1.addTask(t1);
        wd1.getSumPerDay();
        assertEquals(t1.getMinPerTask(), wd1.getSumPerDay());
    }

    /**
     * Test of isWeekday method, of class WorkDay.
     */
    @Test
    public void testIsWeekdayTrue() {
        boolean expResult = true;
        boolean result = wd1.isWeekday();
        assertEquals(expResult, result);
    }
    @Test
    public void testIsWeekdayFalse() {
        boolean expResult = false;
        boolean result = wd7.isWeekday();
        assertEquals(expResult, result);
    }

   
    
}
