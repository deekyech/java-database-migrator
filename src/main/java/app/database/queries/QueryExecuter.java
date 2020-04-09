package app.database.queries;

import app.database.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;

public class QueryExecuter {
	public QueryExecuter(Query query) {
		connection = DatabaseConnection.getConnection();
		this.query = query;
	}
	
	public void execute() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(this.query.getQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Query query;
	Connection connection;
}
