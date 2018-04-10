package java1;

import java.util.HashMap;
import java.util.List;

public class Schedule {
	
	private HashMap<String, List<Client>> schedule;
	
	public Schedule() {
		schedule = new HashMap<String, List<Client>>();
	}
	
	
	public boolean tryAddClientToSchedule(Client client) {
		BookingTimeSlot timeSlot = client.getBooking();
		if(!isTimeAvailable(timeSlot)) {
			return false;
		}
		addToSchedule(client);
		return true;
	}
	
	private boolean isTimeAvailable(BookingTimeSlot timeSlot) {
		String dateDay = timeSlot.getAppointmentDate().toString();
		if(schedule.containsKey(dateDay)) {
			List<Client> clients = schedule.get(dateDay);
			for(Client client : clients) {
				BookingTimeSlot bTimeSlot = client.getBooking();
				if(bTimeSlot.doesTimeSlotsCollide(timeSlot)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void addToSchedule(Client client) {
		String date = client.getBooking().getAppointmentDate().toString();
		List<Client> clients = schedule.get(date);
		clients.add(client);
	}
	
}
