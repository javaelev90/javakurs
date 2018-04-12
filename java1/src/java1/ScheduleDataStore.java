package java1;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import java1.model.Booking;
import java1.model.Day;
import java1.model.Employee;
import java1.model.Month;
import java1.model.Year;

public class ScheduleDataStore extends ForgetfulDataStore  {
	
	private HashMap<Integer, List<Year>> bookingsForEmployees = new HashMap<Integer, List<Year>>();
	
//	public boolean storeBooking(int employeeId, Booking booking) {
//		// TODO Auto-generated method stub
//		List<Year> years = bookingsForEmployees.get(employeeId);
//		if(employee.getSchedule().tryAddClientToSchedule(booking)) {
//			return true;
//		}		
//		return false;
//	}
	
	public Year getBookingsForYear(LocalDateTime date, int employeeId){
		return null;
	}
	
	public Month getBookingsForMonth(LocalDateTime date, int employeeId){
		return null;
	}
	
	public Day getBookingsForDay(LocalDateTime date, int employeeId){
		return null;
	}
	
	
	
}
