package testing;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.ConstraintBuilder;

public class TestDatabaseMigrator {
	public static void main(String[] args) {
		DatabaseMigrator.create("users", (table) -> {
			
			table.id();
			table.addColumn(ColumnBuilder.string("title"));
			table.addColumn(ColumnBuilder.string("slug").nullable().unique());
			table.addColumn(ColumnBuilder.text("body"));
			
			try {
				table.addConstraint(ConstraintBuilder.foreign("user_id").references("id").on("users"));
				table.addConstraint(ConstraintBuilder.primary("id"));
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		});
	}
}
