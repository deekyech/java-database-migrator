package app.migrations;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class CreateUsersTable00000000 {
	
	public void up() {
		DatabaseMigrator.create("users", (table) -> {
			table.id(); // Primary Key
			table.addColumn(ColumnBuilder.string("name"));
			table.addColumn(ColumnBuilder.string("email").unique());
			table.addColumn(ColumnBuilder.string("password"));
		});
	}
	
	public void down() {
	
	}
	
}
