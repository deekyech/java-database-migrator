package app.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * class DatabaseConnection:
 * Class with the sole-purpose of creating Connection object required for
 * all database operations.
 *
 */
public class DatabaseConnection {
	
	/**
	 * getConnection():
	 * static method to return the connection object.
	 *
	 * @return : Connection object
	 */
	public static Connection getConnection() {
		try {
			ParseEnv envParser = new ParseEnv();
			Connection conn = DriverManager.getConnection("jdbc:" + envParser.getDatabaseVariable("DB_CONNECTION") + "://" + envParser.getDatabaseVariable("DB_HOST") + ":" + envParser.getDatabaseVariable("DB_PORT") + "/" + envParser.getDatabaseVariable("DB_DATABASE"), envParser.getDatabaseVariable("DB_USERNAME"), envParser.getDatabaseVariable("DB_PASSWORD"));
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
