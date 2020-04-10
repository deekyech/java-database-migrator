package app.testing;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class TestDatabaseMigrator {
	public static void main(String[] args) {
		DatabaseMigrator.create("users", (table) -> {
			table.id();
			table.addColumn(ColumnBuilder.unsignedBigInteger("user_id"));
			table.addColumn(ColumnBuilder.string("title"));
			table.addColumn(ColumnBuilder.string("slug").nullable().unique());
			table.addColumn(ColumnBuilder.text("body"));
			
			try {
				table.addConstraint(ConstraintBuilder.foreign("user_id")
													 .references("id")
													 .on("users")
													 .onDeleteCascade());
				table.addConstraint(ConstraintBuilder.unique(new String[] {"title", "slug"}));
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		});
	}
}