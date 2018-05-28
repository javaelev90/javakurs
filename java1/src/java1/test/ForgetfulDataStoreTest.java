package java1.test;

import java1.main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ForgetfulDataStoreTest {

    private ForgetfulDataStore forgetfulDataStore;

    @Before
    public void setUp(){
        forgetfulDataStore = new ForgetfulDataStore();
    }

    /**
     * Test store employee in empty data store, allowed
     */
    @Test
    public void testStoreNewEmployee(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);
        Assert.assertTrue(forgetfulDataStore.storeNewEmployee(employee1));
    }

    /**
     * Test store multiple employees with different ids, allowed
     */
    @Test
    public void testStoreMultipleEmployeesCase1(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);
        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setSchedule(schedule);
        Assert.assertTrue(forgetfulDataStore.storeNewEmployee(employee1));
        Assert.assertTrue(forgetfulDataStore.storeNewEmployee(employee2));
    }

    /**
     * Test store multiple employees with colliding ids, not allowed
     */
    @Test
    public void testStoreMultipleEmployeesCase2(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);

        Schedule schedule2 = makeScheduleObject();
        Employee employee2 = new Employee();
        employee2.setId(0);
        employee2.setSchedule(schedule2);
        //First should be ok
        Assert.assertTrue(forgetfulDataStore.storeNewEmployee(employee1));
        //Second should not be ok
        Assert.assertFalse(forgetfulDataStore.storeNewEmployee(employee2));
    }

    /**
     * Employee does not exist
     */
    @Test
    public void testUpdateEmployeeCase1(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);
        Assert.assertFalse(forgetfulDataStore.updateEmployee(employee1.getId(), employee1));
    }

    /**
     * Employee exists
     */
    @Test
    public void testUpdateEmployeeCase2(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);

        forgetfulDataStore.storeNewEmployee(employee1);
        Employee employee1Updated = new Employee();
        employee1Updated.setId(0);
        employee1Updated.setFirstName("Manny");
        Assert.assertTrue(forgetfulDataStore.updateEmployee(employee1Updated.getId(), employee1Updated));
    }

    /**
     * Employee id parameter is not same as Employee.getId()
     */
    @Test
    public void testUpdateEmployeeCase3(){
        Schedule schedule = makeScheduleObject();
        Employee employee1 = new Employee();
        employee1.setId(0);
        employee1.setSchedule(schedule);
        forgetfulDataStore.storeNewEmployee(employee1);
        employee1.setFirstName("Cool man");
        Assert.assertFalse(forgetfulDataStore.updateEmployee(1, employee1));
    }

    /**
     * Employee does not exist
     */
    @Test
    public void testGetEmployeeCase1(){
        Assert.assertNull(forgetfulDataStore.getEmployee(0));
    }

    /**
     * Employee exists
     */
    @Test
    public void testGetEmployeeCase2(){
        Schedule schedule = makeScheduleObject();
        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("Manke");
        employee.setSchedule(schedule);
        forgetfulDataStore.storeNewEmployee(employee);
        Assert.assertEquals(0, forgetfulDataStore.getEmployee(0).getId());
        Assert.assertEquals("Manke", forgetfulDataStore.getEmployee(0).getFirstName());
    }

    /**
     * No employees
     */
    @Test
    public void testGetAllEmployeesCase1(){
        Assert.assertTrue(forgetfulDataStore.getAllEmployees().isEmpty());
    }

    /**
     * Multiple employees
     */
    @Test
    public void testGetAllEmployeesCase2(){
        Schedule schedule = makeScheduleObject();
        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("Molly");
        employee.setSchedule(schedule);

        Schedule schedule2 = makeScheduleObject();
        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setFirstName("Hannah");
        employee2.setSchedule(schedule2);
        forgetfulDataStore.storeNewEmployee(employee);
        forgetfulDataStore.storeNewEmployee(employee2);
        Assert.assertEquals("Molly", forgetfulDataStore.getAllEmployees().get(0).getFirstName());
        Assert.assertEquals("Hannah", forgetfulDataStore.getAllEmployees().get(1).getFirstName());
    }

    /**
     * Store booking to non existing employee
     */
    @Test
    public void testStoreBookingCase1(){
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        Assert.assertFalse(forgetfulDataStore.storeBooking(0, booking));
    }

    /**
     * Store booking to existing employee with empty schedule
     */
    @Test
    public void testStoreBookingCase2(){
        Schedule schedule = makeScheduleObject();

        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("Molly");
        employee.setSchedule(schedule);

        forgetfulDataStore.storeNewEmployee(employee);
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        Assert.assertTrue(forgetfulDataStore.storeBooking(employee.getId(), booking));
    }

    /**
     * Store a booking to existing employee and then try store a colliding booking
     */
    @Test
    public void testStoreBookingCase3(){
        Schedule schedule = makeScheduleObject();

        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("Molly");
        employee.setSchedule(schedule);

        forgetfulDataStore.storeNewEmployee(employee);
        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");
        Booking booking2 = makeBookingObject("2002-11-11T14:33", "2002-11-11T15:44");
        Assert.assertTrue(forgetfulDataStore.storeBooking(employee.getId(), booking));
        Assert.assertFalse(forgetfulDataStore.storeBooking(employee.getId(), booking2));
    }

    /**
     * Store bookings on the same time but for different employees
     */
    @Test
    public void testStoreBookingCase4(){
        Schedule schedule = makeScheduleObject();

        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("Molly");
        employee.setSchedule(schedule);

        Schedule schedule2 = makeScheduleObject();
        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setFirstName("Hannah");
        employee2.setSchedule(schedule2);

        forgetfulDataStore.storeNewEmployee(employee);
        forgetfulDataStore.storeNewEmployee(employee2);

        Booking booking = makeBookingObject("2002-11-11T12:33", "2002-11-11T14:44");

        Assert.assertTrue(forgetfulDataStore.storeBooking(employee.getId(), booking));
        Assert.assertTrue(forgetfulDataStore.storeBooking(employee2.getId(), booking));
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
        LocalTime start = LocalTime.parse("12:33");
        LocalTime stop = LocalTime.parse("14:44");
        ScheduleLimits scheduleLimits = new ScheduleLimits(start, stop);
        return new Schedule(scheduleLimits);
    }
}
