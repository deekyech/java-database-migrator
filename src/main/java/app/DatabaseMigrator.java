package app;

import app.database.Table;

import java.util.function.Consumer;

public class DatabaseMigrator {
	public static void create(String tableName, Consumer<Table> callback) {
		Table table = new Table(tableName);
		callback.accept(table);
		table.printTable();
//		System.out.println("Table: " + tableName + "\nBlueprint:\nID: " + table.getId() + "\nName: " + table.getName());
	}
}
