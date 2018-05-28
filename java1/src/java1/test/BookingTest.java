package java1.test;

import java1.main.Booking;
import java1.main.TimeSlot;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class BookingTest {

    @Test
    public void testCanMakeBookingObject(){
        Booking booking = new Booking();
        TimeSlot timeSlot = new TimeSlot();

        LocalDateTime startTime = LocalDateTime.parse("2018-05-28T08:00");
        LocalDateTime stopTime = LocalDateTime.parse("2018-05-28T09:00");
        timeSlot.setTimeSlotStart(startTime);
        timeSlot.setTimeSlotStop(stopTime);

        booking.setBooking(timeSlot);

        Assert.assertTrue(startTime.isEqual(booking.getBookingTime().getTimeSlotStart()));
        Assert.assertTrue(stopTime.isEqual(booking.getBookingTime().getTimeSlotStop()));
    }
}
