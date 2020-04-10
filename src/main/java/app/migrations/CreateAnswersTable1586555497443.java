
import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class CreateAnswersTable1586555497443 {
	
	public void up() {
		DatabaseMigrator.create("answers", (table) -> {
			table.id(); // Primary Key
			table.addColumn(ColumnBuilder.unsignedBigInteger("user_id"));
			table.addColumn(ColumnBuilder.unsignedBigInteger("question_id"));
			table.addColumn(ColumnBuilder.text("body"));
			table.addColumn(ColumnBuilder.integer("votes_count").defaultValue(0));
			
			table.addConstraint(ConstraintBuilder.foreign("user_id").references("id").on("users").onDelete("cascade"));
			table.addConstraint(ConstraintBuilder.foreign("question_id").references("id").on("questions").onDelete("cascade"));
		});
	}
	
	public void down() {
	
	}
	
}
