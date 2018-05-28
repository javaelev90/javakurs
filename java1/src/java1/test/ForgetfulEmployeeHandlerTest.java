package java1.test;

import java1.main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ForgetfulEmployeeHandlerTest {

    private ForgetfulEmployeeHandler forgetfulEmployeeHandler;
    private ForgetfulDataStore forgetfulDataStore;
    private int employeeCounter;

    @Before
    public void setUp(){
        forgetfulDataStore = new ForgetfulDataStore();
        forgetfulEmployeeHandler = new ForgetfulEmployeeHandler(forgetfulDataStore);
        employeeCounter = 0;
    }

    /**
     * All employees are available
     */
    @Test
    public void testGetAvailableEmployeesCase1(){
        Employee employee1 = putNewEmployeeInDataStore();
        Employee employee2 = putNewEmployeeInDataStore();
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");

        Assert.assertEquals(employee1.getId(),
                forgetfulEmployeeHandler.getAvailableEmployees(booking.getBookingTime()).get(0).getId());
        Assert.assertEquals(employee2.getId(),
                forgetfulEmployeeHandler.getAvailableEmployees(booking.getBookingTime()).get(1).getId());
    }

    /**
     * One out of two employees is available
     */
    @Test
    public void testGetAvailableEmployeesCase2(){
        Employee employee1 = putNewEmployeeInDataStore();
        Employee employee2 = putNewEmployeeInDataStore();
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");

        forgetfulEmployeeHandler.bookEmployee(employee1, booking);

        List<Employee> availableEmployees = forgetfulEmployeeHandler
                .getAvailableEmployees(booking.getBookingTime());

        Assert.assertEquals(1, availableEmployees.size());
        Assert.assertEquals(employee2.getId(), availableEmployees.get(0).getId());

    }

    /**
     * No booked employees
     */
    @Test
    public void testGetBookedEmployeesCase1(){
        Assert.assertTrue(forgetfulEmployeeHandler.getBookedEmployees().isEmpty());
    }

    /**
     * One employee that has a booking
     */
    @Test
    public void testGetBookedEmployeesCase2(){
        Employee employee1 = putNewEmployeeInDataStore();
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);
        Assert.assertEquals(1, forgetfulEmployeeHandler.getBookedEmployees().size());
        Assert.assertEquals(employee1.getId(), forgetfulEmployeeHandler.getBookedEmployees().get(0).getId());
    }

    /**
     * One employee that has one booking and another employee that has zero bookings
     */
    @Test
    public void testGetBookedEmployeesCase3(){
        Employee employee1 = putNewEmployeeInDataStore();
        putNewEmployeeInDataStore();
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);
        Assert.assertEquals(1, forgetfulEmployeeHandler.getBookedEmployees().size());
        Assert.assertEquals(employee1.getId(), forgetfulEmployeeHandler.getBookedEmployees().get(0).getId());
    }

    /**
     * No employees
     */
    @Test
    public void testGetEmployeeWithFewestBookingsOnDateCase1(){
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        Assert.assertNull(forgetfulEmployeeHandler
                .getEmployeeWithFewestBookingsOnDate(booking.getBookingTime(),
                        forgetfulDataStore.getAllEmployees()));
    }

    /**
     * Two employees, two one has one booking the second one booking
     */
    @Test
    public void testGetEmployeeWithFewestBookingsOnDateCase2(){
        Employee employee1 = putNewEmployeeInDataStore();
        Employee employee2 = putNewEmployeeInDataStore();

        Booking booking = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);
        booking = makeBookingObject("2002-11-11T13:00", "2002-11-11T14:00");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);

        booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        forgetfulEmployeeHandler.bookEmployee(employee2, booking);

        Employee employeeWithFewestBookings = forgetfulEmployeeHandler
                .getEmployeeWithFewestBookingsOnDate(booking.getBookingTime(),
                        forgetfulDataStore.getAllEmployees());
        Assert.assertEquals(employee2.getId(), employeeWithFewestBookings.getId());
    }

    /**
     * Two employees, they have equal amount of bookings, the first employee which was found
     * with that amount of bookings will be the one that's returned
     */
    @Test
    public void testGetEmployeeWithFewestBookingsOnDateCase3(){
        Employee employee1 = putNewEmployeeInDataStore();
        Employee employee2 = putNewEmployeeInDataStore();


        Booking booking = makeBookingObject("2002-11-11T12:00", "2002-11-11T13:00");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);
        booking = makeBookingObject("2002-11-11T13:00", "2002-11-11T14:00");
        forgetfulEmployeeHandler.bookEmployee(employee1, booking);

        booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        forgetfulEmployeeHandler.bookEmployee(employee2, booking);
        booking = makeBookingObject("2002-11-11T15:33", "2002-11-11T16:00");
        forgetfulEmployeeHandler.bookEmployee(employee2, booking);

        Employee employeeWithFewestBookings = forgetfulEmployeeHandler
                .getEmployeeWithFewestBookingsOnDate(booking.getBookingTime(),
                        forgetfulDataStore.getAllEmployees());
        Assert.assertEquals(employee1.getId(), employeeWithFewestBookings.getId());
    }


    private Employee putNewEmployeeInDataStore(){
        Schedule schedule = makeScheduleObject();

        Employee employee = new Employee();
        employee.setId(employeeCounter);
        employee.setSchedule(schedule);
        forgetfulDataStore.storeNewEmployee(employee);

        employeeCounter++;
        return employee;
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

    private Schedule makeScheduleObject(){
        LocalTime start = LocalTime.parse("08:00");
        LocalTime stop = LocalTime.parse("16:00");
        ScheduleLimits scheduleLimits = new ScheduleLimits(start, stop);
        return new Schedule(scheduleLimits);
    }

}
