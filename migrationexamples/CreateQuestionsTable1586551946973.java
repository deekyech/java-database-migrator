package app.migrations;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class CreateQuestionsTable1586551946973 {
	
	public void up() {
		DatabaseMigrator.create("questions", (table) -> {
			table.id(); // Primary Key
			table.addColumn(ColumnBuilder.string("title"));
			table.addColumn(ColumnBuilder.string("slug").unique());
			table.addColumn(ColumnBuilder.text("body"));
			table.addColumn(ColumnBuilder.unsignedInteger("votes_count").defaultValue(0));
			table.addColumn(ColumnBuilder.unsignedInteger("answers_count").defaultValue(0));
			table.addColumn(ColumnBuilder.integer("views_count").defaultValue(0));
			table.addColumn(ColumnBuilder.integer("best_answer_id"));
			table.addColumn(ColumnBuilder.unsignedBigInteger("user_id"));
			
			table.addConstraint(ConstraintBuilder.foreign("user_id")
					.references("id")
					.on("users")
					.onDelete("cascade"));
			
		});
	}
	
	public void down() {
	
	}
	
}
