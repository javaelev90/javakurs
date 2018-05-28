package java1.test;

import java1.main.Booking;
import java1.main.Schedule;
import java1.main.ScheduleLimits;
import java1.main.TimeSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleTest {


    private Schedule schedule;

    @Before
    public void setUp(){
        LocalTime start = LocalTime.parse("08:00");
        LocalTime stop = LocalTime.parse("16:00");
        ScheduleLimits scheduleLimits = new ScheduleLimits(start, stop);
        schedule = new Schedule(scheduleLimits);
    }

    private Booking makeBookingObject(String startString, String stopString){
        TimeSlot timeSlot = new TimeSlot();
        LocalDateTime start = LocalDateTime.parse(startString);
        LocalDateTime stop = LocalDateTime.parse(stopString);
        timeSlot.setTimeSlotStart(start);
        timeSlot.setTimeSlotStop(stop);
        Booking booking = new Booking();
        booking.setBooking(timeSlot);
        return booking;
    }

    @Test
    public void testCanMakeSchedule(){
        LocalTime start = LocalTime.parse("12:33");
        LocalTime stop = LocalTime.parse("14:44");
        ScheduleLimits scheduleLimits = new ScheduleLimits(start, stop);
        Schedule schedule = new Schedule(scheduleLimits);
        Assert.assertTrue(schedule.isEmpty());
        Assert.assertEquals(scheduleLimits.toString(),schedule.getScheduleLimits().toString());
    }

    /**
     * Try add booking to empty schedule, should work
     */
    @Test
    public void testTryAddToScheduleCase1(){
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        Assert.assertTrue(schedule.tryAddClientToSchedule(booking));
    }

    /**
     * Try add multiple non-colliding bookings to empty schedule, should work
     */
    @Test
    public void testTryAddToScheduleCase2(){
        Booking booking1 = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        Booking booking2 = makeBookingObject("2002-11-11T13:00", "2002-11-11T14:00");
        Booking booking3 = makeBookingObject("2002-11-11T14:00", "2002-11-11T15:00");

        Assert.assertTrue(schedule.tryAddClientToSchedule(booking2));
        Assert.assertTrue(schedule.tryAddClientToSchedule(booking1));
        Assert.assertTrue(schedule.tryAddClientToSchedule(booking3));
    }

    /**
     * Try add colliding bookings to schedule, should not work
     */
    @Test
    public void testTryAddToScheduleCase3(){
        Booking booking1 = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        Booking booking2 = makeBookingObject("2002-11-11T12:59", "2002-11-11T14:00");
        Assert.assertTrue(schedule.tryAddClientToSchedule(booking2));
        Assert.assertFalse(schedule.tryAddClientToSchedule(booking1));
    }


    /**
     * Time slot not within limits
     */
    @Test
    public void testIsTimeSlotAvailableCase1(){
        Booking booking = makeBookingObject("2002-11-11T07:00", "2002-11-11T13:00");
        Assert.assertFalse(schedule.isTimeSlotAvailable(booking.getBookingTime()));
    }

    /**
     * Time slot is on an empty day
     */
    @Test
    public void testIsTimeSlotAvailableCase2(){
        Booking booking = makeBookingObject("2002-11-11T08:00", "2002-11-11T16:00");
        Assert.assertTrue(schedule.isTimeSlotAvailable(booking.getBookingTime()));
    }

    /**
     * Time slot is colliding with another time slot
     */
    @Test
    public void testIsTimeSlotAvailableCase3(){
        Booking booking1 = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        Booking booking2 = makeBookingObject("2002-11-11T12:59", "2002-11-11T14:00");
        Assert.assertTrue(schedule.isTimeSlotAvailable(booking2.getBookingTime()));
        //Adding booking 2 to schedule
        schedule.tryAddClientToSchedule(booking2);
        Assert.assertFalse(schedule.isTimeSlotAvailable(booking1.getBookingTime()));
    }

    /**
     * Booking same time different day
     */
    @Test
    public void testIsTimeSlotAvailableCase4(){
        Booking booking1 = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        Booking booking2 = makeBookingObject("2002-11-12T12:00", "2002-11-12T13:00");
        Assert.assertTrue(schedule.isTimeSlotAvailable(booking2.getBookingTime()));
        //Adding booking 2 to schedule
        schedule.tryAddClientToSchedule(booking2);
        Assert.assertTrue(schedule.isTimeSlotAvailable(booking1.getBookingTime()));
    }

    /**
     * Number of appointments on an empty day
     */
    @Test
    public void testGetNumberOfAppointmentsCase1(){
        Booking booking = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        TimeSlot timeSlot = booking.getBookingTime();

        Assert.assertEquals(0, schedule.getNumberOfAppointmentsOnDate(timeSlot));
    }

    /**
     * Number of appointments on a non empty day
     */
    @Test
    public void testGetNumberOfAppointmentsCase2(){
        Booking booking = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        Booking booking2 = makeBookingObject("2002-11-11T09:00", "2002-11-11T11:00");
        TimeSlot timeSlot = booking.getBookingTime();
        schedule.tryAddClientToSchedule(booking);
        schedule.tryAddClientToSchedule(booking2);
        Assert.assertEquals(2, schedule.getNumberOfAppointmentsOnDate(timeSlot));
    }

}
