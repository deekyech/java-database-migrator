package app.migrations;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.ConstraintBuilder;

public class CreateUserAddressesTable1586383757039 {
	public static void main(String[] args) {
		DatabaseMigrator.create("user-addresses", (table) -> {
			table.id();	

		});
	}
}