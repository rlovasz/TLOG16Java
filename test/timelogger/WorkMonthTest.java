/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author precognox
 */
public class WorkMonthTest {
    
    public WorkMonthTest() {
    }
    Task t1;
    Task t2;
    
    WorkDay wd1;
    WorkDay wd2;
    WorkDay wd3;
    WorkDay wd4;
    WorkDay wd5;
    
    WorkMonth wm1;
    @Before
    public void setUp() {
        wd1 = new WorkDay(420);
        wd2 = new WorkDay();
        wd3 = new WorkDay(420,LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd4 = new WorkDay(LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd5 = new WorkDay(LocalDate.of(2016, Month.AUGUST, 28));
        
        t1 = new Task("1856","This is a comment",LocalTime.of(7, 30), LocalTime.of(8, 45));
        t2 = new Task("1486","This is a comment",LocalTime.of(8, 45), LocalTime.of(9, 45));
        
        wm1=new WorkMonth();
    }
    @Test
    public void testGetDays() {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        List<WorkDay> result = wm1.getDays();
        for(WorkDay wd:result){
        assertEquals(420, wd.getRequiredMinPerDay());}
    }
    
    /**
     * Test of getSumPerMonth method, of class WorkMonth.
     */
    @Test
    public void testGetSumPerMonth() {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = 135;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExtraMinPerMonth method, of class WorkMonth.
     */
    @Test
    public void testGetExtraMinPerMonth() {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = -705;
        long result = wm1.getExtraMinPerMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of addWorkDay method, of class WorkMonth.
     */
    @Test
    public void testAddWorkDayWeekday() {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        assertEquals(wd1.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test
    public void testAddWorkDayWeekendTrue() {
        wd5.addTask(t1);
        wm1.addWorkDay(wd5,true);
        assertEquals(wd5.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test
    public void testAddWorkDayWeekendFalse() {
        wm1.addWorkDay(wd5);
        assertEquals(0, wm1.getSumPerMonth());
    }
    
}
