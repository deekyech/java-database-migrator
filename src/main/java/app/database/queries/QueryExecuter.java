package app.database.queries;

import app.database.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;


/**
 * class QueryExecuter:
 * A class that will provide methods required for query execution.
 */
public class QueryExecuter {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public QueryExecuter(Query query) {
		connection = DatabaseConnection.getConnection();
		this.query = query;
	}
	
	/**
	 * execute():
	 * This method will execute the query object of this class.
	 */
	public void execute() {
		try {
			Statement statement = connection.createStatement();
			statement.execute(this.query.getQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Query query;
	private Connection connection;
}
