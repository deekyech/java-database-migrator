package app.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConnection() {
		try {
			ParseEnv envParser = new ParseEnv();
			Connection conn = DriverManager.getConnection("jdbc:" + envParser.getDatabaseVariable("DB_CONNECTION") + "://" + envParser.getDatabaseVariable("DB_HOST") + ":" + envParser.getDatabaseVariable("DB_PORT") + "/" + envParser.getDatabaseVariable("DB_DATABASE"), envParser.getDatabaseVariable("DB_USERNAME"), envParser.getDatabaseVariable("DB_PASSWORD"));
			return conn;
		} catch (SQLException e) {
			return null;
		}
	}
}
