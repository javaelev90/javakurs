package java1;

public class Main {

	public static void main(String[] args) {

		Application app = new Application();
		app.setup();
		app.run();
		// Gson gson = new Gson();
		// Booking booking = new Booking();
		// TimeSlot slot = new TimeSlot();
		// JsonDataStore store = new JsonDataStore();
		// slot.setTimeSlotStart(DateTimeValidator.getValidDateTime("2002-11-11
		// 13:30"));
		// slot.setTimeSlotTop(DateTimeValidator.getValidDateTime("2002-11-11 15:30"));
		//
		// booking.setBooking(slot);
		//
		// ScheduleLimits limits = new
		// ScheduleLimits(DateTimeValidator.getValidTime("08:00"),
		// DateTimeValidator.getValidTime("18:00"));
		// Schedule schedule = new Schedule(limits);
		// schedule.tryAddClientToSchedule(booking);
		// int id = 1;
		// String jsonString = gson.toJson(schedule);
		//
		// Path path = FileHandler.getFilePathForEmployee(id);
		// FileHandler.writeToFile(jsonString, path);
		//
		// booking = new Booking();
		// slot = new TimeSlot();
		// slot.setTimeSlotStart(DateTimeValidator.getValidDateTime("2002-11-11
		// 15:30"));
		// slot.setTimeSlotTop(DateTimeValidator.getValidDateTime("2002-11-11 16:30"));
		// booking.setBooking(slot);
		//
		// store.saveBooking(1, booking);
		//
		// Optional<String> fromJson = FileHandler.readFromFile(path);
		// String str = fromJson.get();
		// Schedule s = gson.fromJson(str, Schedule.class);
		// System.out.println(s.toString());
		//
		// booking = new Booking();
		// slot = new TimeSlot();
		// slot.setTimeSlotStart(DateTimeValidator.getValidDateTime("2002-11-11
		// 16:30"));
		// slot.setTimeSlotTop(DateTimeValidator.getValidDateTime("2002-11-11 17:30"));
		//
		// booking.setBooking(slot);
		// store.saveBooking(1, booking);
		//
		// fromJson = FileHandler.readFromFile(path);
		// str = fromJson.get();
		// s = gson.fromJson(str, Schedule.class);
		// System.out.println(s.toString());
		//
		// store.deleteBookingOnDateTime(1, booking);
		//
		// fromJson = FileHandler.readFromFile(path);
		// str = fromJson.get();
		// s = gson.fromJson(str, Schedule.class);
		// System.out.println(s.toString());

	}

}
