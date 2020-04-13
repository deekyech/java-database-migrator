package app;

import app.database.AlterTable;
import app.database.CreateTable;
import app.database.Table;
import app.database.queries.Query;
import app.database.queries.QueryExecuter;

import java.util.function.Consumer;

/**
 * class DatabaseMigrator:
 * The outer most class that provides static methods called by the generated
 * migration files. This class is responsible for accepting the data from
 * all the migration files and convert into database queries and execute them.
 */
public class DatabaseMigrator {
	
	/**
	 * create():
	 * Static method that will be called by migration files created to create
	 * tables. It provides a callback function using Java's Functional Interfaces
	 * to accept all parameters from the migration files.
	 *
	 * @param tableName : Name of table that is to be created.
	 * @param callback : A callback function to accept tables attributes and
	 *                 constraints.
	 */
	public static void create(String tableName, Consumer<Table> callback) {
		Table table = new CreateTable(tableName);
		callback.accept(table);
		new QueryExecuter(table.toQuery()).execute();
		System.out.println("Query Executed: " + table.toQuery());
	}
	
	
	/**
	 * table():
	 * Static method that will perform all Alter operations.
	 * @param tableName
	 * @param callback
	 */
	public static void table(String tableName, Consumer<Table> callback) {
		Table table = new AlterTable(tableName);
		callback.accept(table);
		new QueryExecuter(table.toQuery()).execute();
		System.out.println("Query Executed: " + table.toQuery());
	}
	
	/**
	 * drop():
	 * static method to perform all drop operations.
	 * @param tableName
	 */
	public static void drop(String tableName) {
		String query = "DROP TABLE " + tableName;
		new QueryExecuter(new Query(query)).execute();
		System.out.println("Query Executed: " + query);
	}
	
	public static void dropIfExists(String tableName) {
		String query = "DROP TABLE IF EXISTS " + tableName;
		new QueryExecuter(new Query(query)).execute();
		System.out.println("Query Executed: " + query);
	}
	
	/**
	 * rename():
	 * static method to perform all rename operations.
	 * @param from
	 * @param to
	 */
	public static void rename(String from, String to) {
		String query = "ALTER TABLE " + from + " RENAME TO " + to;
		new QueryExecuter(new Query(query)).execute();
		System.out.println("Query Executed: " + query);
	}
}
