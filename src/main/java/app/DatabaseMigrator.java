package app;

import app.database.Table;

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
		Table table = new Table(tableName);
		callback.accept(table);
		table.printTable();
//		System.out.println("Table: " + tableName + "\nBlueprint:\nID: " + table.getId() + "\nName: " + table.getName());
	}
}
