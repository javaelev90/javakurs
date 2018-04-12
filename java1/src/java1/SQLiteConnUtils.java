package java1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.sqlite.SQLiteDataSource;

public class SQLiteConnUtils {
	
	public static Connection getConnection() throws ClassNotFoundException {
		SQLiteDataSource dataSource = new SQLiteDataSource();
		Properties properties = new Properties();
		dataSource.setUrl(properties.getProperty("SQLITE_DB_URL"));
		
		try(Connection conn = dataSource.getConnection()){
			try(Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("CREATE TABLE employees.db")){
				System.out.println("made table");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
