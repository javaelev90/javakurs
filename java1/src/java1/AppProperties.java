package java1;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

public class AppProperties {

	private static Properties props;
	private static boolean loadedProperties = false;
	
	public static boolean loadProperties() {
		props = new Properties();
		try(Reader reader = Files.newBufferedReader(Paths.get("./resources/app.properties"))){
			props.load(reader);
			loadedProperties = true;
			return true;
		} catch (IOException e) {
			System.out.println("Could not load properties!");
			return false;
		}
	}
	
	public static Optional<Properties> getProperties() {
		if(loadedProperties) {
			return Optional.of(props);
		}
		return Optional.empty();
	}
	
}
