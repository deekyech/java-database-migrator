package app.database;

public class ConstraintBuilder {
	
	
	
	public static ForeignKeyConstraintBuilder foreign(String fieldName) {
		return new ForeignKeyConstraintBuilder(new ForeignKeyConstraint(fieldName));
	}
	
	public static PrimaryKeyConstraintBuilder primary(String fieldName) {
		return new PrimaryKeyConstraintBuilder(new PrimaryKeyConstraint(fieldName));
	}
	
}
