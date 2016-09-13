package timelogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    WorkDay wd6;
    WorkMonth wm2;
    WorkMonth wm1;
    
    @Before
    public void setUp() throws NoTaskIdException, InvalidTaskIdException, NegativeMinutesOfWorkException, FutureWorkException {
        wd1 = new WorkDay(420, 2016, 9, 2);
        wd2 = new WorkDay();
        wd3 = new WorkDay(420, 2016, 9, 1);
        wd4 = new WorkDay(2016, 9, 1);
        wd5 = new WorkDay(2016, 9, 10);
        wd6 = new WorkDay(2016, 8, 30);
        
        t1 = new Task("1856", "This is a comment", 7, 30, 8, 45);
        t2 = new Task("1486", "This is a comment", 8, 45, 9, 45);
        
        wm1 = new WorkMonth(2016 ,9);
        wm2 = new WorkMonth(2016, 8);
    }
    
    @Test
    public void testGetSumPerMonthNormal() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = 135;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetSumPerMonthNoDays() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        long expResult = 0;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetExtraMinPerMonthNormal() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = -705;
        long result = wm1.getExtraMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetExtraMinPerMonthNoDays() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        long expResult = 0;
        long result = wm1.getExtraMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRequiredMinPerMonthNormal() throws WeekendNotEnabledException, NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wm1.addWorkDay(wd1);
        wm1.addWorkDay(wd3);
        long expResult = 840;
        long result = wm1.getRequiredMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRequiredMinPerMonthNoDays() throws WeekendNotEnabledException, NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {        
        long expResult = 0;
        long result = wm1.getRequiredMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddWorkDayWeekday() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        assertEquals(wd1.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test
    public void testAddWorkDayWeekendTrue() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wd5.addTask(t1);
        wm1.addWorkDay(wd5, true);
        assertEquals(wd5.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test(expected = WeekendNotEnabledException.class)
    public void testAddWorkDayWeekendFalse() throws WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotNewDateException, NotTheSameMonthException {
        wm1.addWorkDay(wd5);
    }
    
    @Test(expected = NotNewDateException.class)
    public void testAddWorkDayNewFalse() throws WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotNewDateException, NotMultipleQuarterHourException, NotSeparatedTaskTimesException, NotTheSameMonthException {
        wm1.addWorkDay(wd4);
        wm1.addWorkDay(wd3);
    }
    
    @Test
    public void testAddWorkDayNewTrue() throws NotMultipleQuarterHourException, WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException, NotTheSameMonthException {
        wm1.addWorkDay(wd4);
        wm1.addWorkDay(wd1);
        assertEquals(wd1.getRequiredMinPerDay()+wd4.getRequiredMinPerDay(), wm1.getRequiredMinPerMonth());
    }
    
    
    @Test(expected = NotTheSameMonthException.class)
    public void testAddWorkDaySameMonthFalse() throws WeekendNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotNewDateException, NotMultipleQuarterHourException, NotSeparatedTaskTimesException, NotTheSameMonthException {
        wm1.addWorkDay(wd4);
        wm1.addWorkDay(wd6);
    }
    
    @Test
    public void testIsNewDateFalse() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        boolean expResult = false;
        wm1.addWorkDay(wd3);
        boolean result = wm1.isNewDate(wd4);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsNewDateTrue() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        boolean expResult = true;
        wm1.addWorkDay(wd2);
        boolean result = wm1.isNewDate(wd4);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsNewDateEmptyDays() {
        boolean expResult = true;
        boolean result = wm1.isNewDate(wd1);
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testIsSameMonthTrue() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        boolean expResult = true;
        wm1.addWorkDay(wd1);
        boolean result = wm1.isSameMonth(wd2);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsSameMonthFalse() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        boolean expResult = false;
        wm1.addWorkDay(wd1);
        boolean result = wm1.isSameMonth(wd6);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsSameMonthEmptyDays() throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        boolean expResult = true;
        boolean result = wm1.isSameMonth(wd5);
        assertEquals(expResult, result);
    }
}
