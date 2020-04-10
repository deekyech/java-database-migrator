package app.migrations;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class CreateVotesTable1586554591662 {
	
	public void up() {
		DatabaseMigrator.create("votes", (table) -> {
			table.id(); // Primary Key
			table.addColumn(ColumnBuilder.unsignedBigInteger("user_id"));
			table.addColumn(ColumnBuilder.unsignedBigInteger("vote_id"));
			table.addColumn(ColumnBuilder.string("vote_type"));
			table.addColumn(ColumnBuilder.integer("vote"));
			
			table.addConstraint(ConstraintBuilder.unique(new String[] {"user_id", "vote_id", "vote_type"}));
			table.addConstraint(ConstraintBuilder.foreign("user_id").references("id").on("users").onDelete("cascade"));
		});
	}
	
	public void down() {
	
	}
	
}
