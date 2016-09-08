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
    
    WorkMonth wm1;
    
    @Before
    public void setUp() throws NoTaskIdException {
        wd1 = new WorkDay(420);
        wd2 = new WorkDay();
        wd3 = new WorkDay(420, LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd4 = new WorkDay(LocalDate.of(2016, Month.SEPTEMBER, 1));
        wd5 = new WorkDay(LocalDate.of(2016, Month.AUGUST, 28));
        
        t1 = new Task("1856", "This is a comment", LocalTime.of(7, 30), LocalTime.of(8, 45));
        t2 = new Task("1486", "This is a comment", LocalTime.of(8, 45), LocalTime.of(9, 45));
        
        wm1 = new WorkMonth();
    }

    @Test
    public void testGetSumPerMonthNormal() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = 135;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetSumPerMonthNoDays() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        long expResult = 0;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetExtraMinPerMonthNormal() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        wd3.addTask(t2);
        wm1.addWorkDay(wd3);
        long expResult = -705;
        long result = wm1.getExtraMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetExtraMinPerMonthNoDays() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {
        long expResult = 0;
        long result = wm1.getExtraMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRequiredMinPerMonthNormal() throws WeekendIsNotEnabledException, NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wm1.addWorkDay(wd1);
        wm1.addWorkDay(wd3);
        long expResult = 840;
        long result = wm1.getRequiredMinPerMonth();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRequiredMinPerMonthNoDays() throws WeekendIsNotEnabledException, NotMultipleQuarterHourException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException {        
        long expResult = 0;
        long result = wm1.getRequiredMinPerMonth();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddWorkDayWeekday() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wd1.addTask(t1);
        wm1.addWorkDay(wd1);
        assertEquals(wd1.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test
    public void testAddWorkDayWeekendTrue() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wd5.addTask(t1);
        wm1.addWorkDay(wd5, true);
        assertEquals(wd5.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test(expected = WeekendIsNotEnabledException.class)
    public void testAddWorkDayWeekendFalse() throws WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotNewDateException {
        wm1.addWorkDay(wd5);
    }
    
    @Test(expected = NotNewDateException.class)
    public void testAddWorkDayNewFalse() throws WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotNewDateException, NotMultipleQuarterHourException, NotSeparatedTaskTimesException {
        wd3.addTask(t1);
        wd4.addTask(t2);
        wm1.addWorkDay(wd4);
        wm1.addWorkDay(wd3);
        long expResult = 135;
        long result = wm1.getSumPerMonth();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddWorkDayNewTrue() throws NotMultipleQuarterHourException, WeekendIsNotEnabledException, NotExpectedTimeOrderException, EmptyTimeFieldException, NotSeparatedTaskTimesException, NotNewDateException {
        wm1.addWorkDay(wd4);
        wm1.addWorkDay(wd1);
        assertEquals(wd5.getSumPerDay(), wm1.getSumPerMonth());
    }
    
    @Test
    public void testIsNewDateFalse() throws WeekendIsNotEnabledException, NotNewDateException {
        boolean expResult = false;
        wm1.addWorkDay(wd3);
        boolean result = wm1.isNewDate(wd4);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsNewDateTrue() throws WeekendIsNotEnabledException, NotNewDateException {
        boolean expResult = true;
        wm1.addWorkDay(wd2);
        boolean result = wm1.isNewDate(wd4);
        assertEquals(expResult, result);
    }
}
