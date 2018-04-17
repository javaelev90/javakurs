package java1;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

public class FileHandler {

	public static synchronized void writeToFile(String text, Path path) {
		byte[] bytes = text.getBytes();

		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, TRUNCATE_EXISTING))) {
			out.write(bytes);
		} catch (IOException e) {
			System.out.println("IOException was thrown when trying to write to file: " + path.toString());
		}

	}

	public static synchronized Optional<String> readFromFile(Path path) {
		if(!doesFileExist(path)) return Optional.empty();
		try (Stream<String> lines = Files.lines(path)) {
			StringBuilder data = new StringBuilder();
			lines.forEach(line -> data.append(line));
			return Optional.of(data.toString());
		} catch (IOException e) {
			System.out.println("IOException was thrown when trying to read from file: " + path.toString());
			return Optional.empty();
		}
	}
	
	public static boolean deleteFile(Path path) {
		if(!doesFileExist(path)) return false;
		try {
			Files.delete(path);
			return false;
		} catch (IOException e) {
			System.out.format("Could not delete file %s \n", path);
			return false;
		}
	}
	
	public static boolean createFile(Path path) {
		if(doesFileExist(path)) return false;
		try {
			Files.createFile(path);
			return true;
		} catch (IOException e) {
			System.out.format("Could not create file %s \n", path);
			return false;
		}
		
	}

	private static boolean doesFileExist(Path path) {
		return Files.exists(path);
	}
	
	public static Path getFilePathForEmployeeSchedule(int employeeId) {
		String path = System.getProperty("employeeSchedulePath");
		String type = System.getProperty("employeeScheduleType");
		return Paths.get(path + employeeId + type);
	}

	public static Path getFilePathToEmployeeFile() {
		String path = System.getProperty("employeeFilePath");
		return Paths.get(path);
	}
}
