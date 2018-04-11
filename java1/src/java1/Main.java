package java1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
//		
//		String str = "2015-03-15";
//		String timestart1 = "15:00";
//		String timestop1 = "16:54";
//		LocalDate dateTime1 = LocalDate.parse(str, formatter);
//		LocalTime localStart =  LocalTime.parse(timestart1, formatter2);
//		LocalTime localstop =  LocalTime.parse(timestop1, formatter2);
//		
//		BookingTimeSlot timeSlot1 = new BookingTimeSlot();
//		timeSlot1.setAppointmentDate(dateTime1);
//		timeSlot1.setEventStartTime(localStart);
//		timeSlot1.setEventStopTime(localstop);
//		
//		
//		String str2 = "2015-03-15";
//		String timestart2 = "14:55";
//		String timestop2 = "15:01";
//	    LocalDate dateTime2 = LocalDate.parse(str2, formatter);
//	    LocalTime localStart2 =  LocalTime.parse(timestart2, formatter2);
//	    LocalTime localstop2 =  LocalTime.parse(timestop2, formatter2);
//	    
//		BookingTimeSlot timeSlot2 = new BookingTimeSlot();
//		timeSlot2.setAppointmentDate(dateTime2);
//		timeSlot2.setEventStartTime(localStart2);
//		timeSlot2.setEventStopTime(localstop2);
//		System.out.println("Will timeslots collide: "+timeSlot1.doesTimeSlotsCollide(timeSlot2));
//		System.out.println("Timeslot 1: " + timeSlot1.toString());
//		System.out.println("Timeslot 2: " + timeSlot2.toString());
//		//System.out.println(timeSlot1.doesTimeSlotsCollide(timeSlot2));
		
		Application app = new Application();
		app.setup();
		app.run();
		
	}	

}
