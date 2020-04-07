package app;

import app.database.Blueprint;

import java.util.function.Consumer;

public class DatabaseMigrator {
	public static void create(String tableName, Consumer<Blueprint> callback) {
		Blueprint table = new Blueprint(tableName);
		callback.accept(table);
//		System.out.println("Table: " + tableName + "\nBlueprint:\nID: " + table.getId() + "\nName: " + table.getName());
	}
}
