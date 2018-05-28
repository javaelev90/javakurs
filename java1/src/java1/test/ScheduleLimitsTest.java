package java1.test;

import java1.main.Schedule;
import java1.main.ScheduleLimits;
import java1.main.TimeSlot;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleLimitsTest {

    @Test
    public void testCanMakeScheduleLimits(){

        LocalTime startTime = LocalTime.now();
        LocalTime stopTime = LocalTime.now();

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        Assert.assertTrue(scheduleLimits.toString().equals(startTime + "-" + stopTime));

    }

    /**
     * Checking if time slot is spanning over midnight, which is not allowed
     */
    @Test
    public void testIsWithinLimitsDifferentDay(){
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime stopTime = LocalTime.parse("16:00");

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        TimeSlot timeSlot = new TimeSlot();
        //Time slots are from 12:00 pm the 11th to 01:00 am the 12th
        timeSlot.setTimeSlotStart(LocalDateTime.parse("2018-11-11T12:00"));
        timeSlot.setTimeSlotStop(LocalDateTime.parse("2018-11-12T01:00"));


        Assert.assertFalse(scheduleLimits.isWithinLimits(timeSlot));
    }


    /**
     * Test when time slot starts before scheduleLimits start time.
     */
    @Test
    public void testIsWithinLimitsSameDayCase1(){
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime stopTime = LocalTime.parse("16:00");

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T07:59");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T09:00");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);
        //Assert false
        Assert.assertFalse(scheduleLimits.isWithinLimits(timeSlot2));

    }

    /**
     * Test when time slot stop time is after scheduleLimits stop time
     */
    @Test
    public void testIsWithinLimitsSameDayCase2(){
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime stopTime = LocalTime.parse("16:00");

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T15:00");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T16:01");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);
        //Assert false
        Assert.assertFalse(scheduleLimits.isWithinLimits(timeSlot2));

    }


    /**
     * Test when time slot envelope scheduleLimits start/stop time
     */
    @Test
    public void testIsWithinLimitsSameDayCase3(){
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime stopTime = LocalTime.parse("16:00");

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T07:59");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T16:01");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);
        //Assert false
        Assert.assertFalse(scheduleLimits.isWithinLimits(timeSlot2));

    }

    /**
     * Test when a time slot is within schedule limits, a valid case
     */
    @Test
    public void testIsWithinLimitsSameDayCase4(){
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime stopTime = LocalTime.parse("16:00");

        ScheduleLimits scheduleLimits = new ScheduleLimits(startTime, stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T08:00");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T16:00");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);

        Assert.assertTrue(scheduleLimits.isWithinLimits(timeSlot2));

    }

}
