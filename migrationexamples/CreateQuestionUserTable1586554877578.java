
import app.DatabaseMigrator;
import app.database.ColumnBuilder;
import app.database.constraints.ConstraintBuilder;

public class CreateQuestionUserTable1586554877578 {
	
	public void up() {
		DatabaseMigrator.create("question_user", (table) -> {
			table.id(); // Primary Key
			table.addColumn(ColumnBuilder.unsignedBigInteger("user_id"));
			table.addColumn(ColumnBuilder.unsignedBigInteger("question_id"));
			
			table.addConstraint(ConstraintBuilder.foreign("user_id").references("id").on("users").onDelete("cascade"));
			table.addConstraint(ConstraintBuilder.foreign("question_id").references("id").on("questions").onDelete("cascade"));
		});
	}
	
	public void down() {
	
	}
	
	public static void main(String[] args) {
		new CreateQuestionUserTable1586554877578().up();
	}
}
