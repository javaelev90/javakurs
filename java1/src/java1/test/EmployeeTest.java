package java1.test;

import java1.main.Employee;
import java1.main.Schedule;
import java1.main.ScheduleLimits;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class EmployeeTest {

    @Test
    public void testCanMakeEmployee(){
        String firstName = "Mark";
        String lastName = "Succ";
        int id = 0;
        LocalTime start = LocalTime.now();
        LocalTime stop = LocalTime.now();
        ScheduleLimits scheduleLimits = new ScheduleLimits(start, stop);
        Schedule schedule = new Schedule(scheduleLimits);

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setId(id);
        employee.setSchedule(schedule);

        Assert.assertEquals(firstName, employee.getFirstName());
        Assert.assertEquals(lastName, employee.getLastName());
        Assert.assertEquals(id, employee.getId());
        Assert.assertEquals(schedule, employee.getSchedule());
        Assert.assertEquals(schedule.isEmpty(), employee.getSchedule().isEmpty());
        Assert.assertEquals(schedule.getScheduleLimits().toString(), employee.getSchedule().getScheduleLimits().toString());

    }

    @Test
    public void testGetFullName(){
        String firstName = "Mark";
        String lastName = "Succ";

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        Assert.assertEquals(firstName+ " " + lastName, employee.getFullName());

    }


}
