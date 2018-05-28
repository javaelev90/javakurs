package java1.test;

import java1.main.TimeSlot;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TimeSlotTest {


    @Test
    public void testCanMakeTimeSlot(){
        TimeSlot timeSlot = new TimeSlot();
        LocalDateTime startTime = LocalDateTime.parse("2018-11-11T12:00");
        LocalDateTime stopTime = LocalDateTime.parse("2018-11-11T13:00");
        timeSlot.setTimeSlotStart(startTime);
        timeSlot.setTimeSlotStop(stopTime);

        Assert.assertTrue(timeSlot.getTimeSlotStart().isEqual(startTime));
        Assert.assertTrue(timeSlot.getTimeSlotStop().isEqual(stopTime));
    }


    /**
     * Test collision if stop time is after start time of another time slot
     */
    @Test
    public void testDoTimeSlotsCollideCase1(){

        TimeSlot timeSlot = new TimeSlot();
        LocalDateTime startTime = LocalDateTime.parse("2018-11-11T12:00");
        LocalDateTime stopTime = LocalDateTime.parse("2018-11-11T13:00");
        timeSlot.setTimeSlotStart(startTime);
        timeSlot.setTimeSlotStop(stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T11:00");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T12:01");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);

        Assert.assertTrue(timeSlot.doTimeSlotsCollide(timeSlot2));
        Assert.assertTrue(timeSlot2.doTimeSlotsCollide(timeSlot));

    }

    /**
     * Test collision if start time is before stop time of another time slot
     */
    @Test
    public void testDoTimeSlotsCollideCase2(){

        TimeSlot timeSlot = new TimeSlot();
        LocalDateTime startTime = LocalDateTime.parse("2018-11-11T12:00");
        LocalDateTime stopTime = LocalDateTime.parse("2018-11-11T13:00");
        timeSlot.setTimeSlotStart(startTime);
        timeSlot.setTimeSlotStop(stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T12:59");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T14:00");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);

        Assert.assertTrue(timeSlot.doTimeSlotsCollide(timeSlot2));
        Assert.assertTrue(timeSlot2.doTimeSlotsCollide(timeSlot));
    }

    /**
     * Test collision if one time slot is enveloped by another time slot
     */
    @Test
    public void testDoTimeSlotsCollideCase3(){

        TimeSlot timeSlot = new TimeSlot();
        LocalDateTime startTime = LocalDateTime.parse("2018-11-11T12:00");
        LocalDateTime stopTime = LocalDateTime.parse("2018-11-11T13:00");
        timeSlot.setTimeSlotStart(startTime);
        timeSlot.setTimeSlotStop(stopTime);

        TimeSlot timeSlot2 = new TimeSlot();
        LocalDateTime startTime2 = LocalDateTime.parse("2018-11-11T12:01");
        LocalDateTime stopTime2 = LocalDateTime.parse("2018-11-11T12:59");
        timeSlot2.setTimeSlotStart(startTime2);
        timeSlot2.setTimeSlotStop(stopTime2);

        Assert.assertTrue(timeSlot.doTimeSlotsCollide(timeSlot2));
        Assert.assertTrue(timeSlot2.doTimeSlotsCollide(timeSlot));
    }


}
