package app.database;

public class ForeignKeyConstraintBuilder implements Builder {
	
	public ForeignKeyConstraintBuilder(ForeignKeyConstraint foreignKeyConstraint) {
		this.foreignKeyConstraint = foreignKeyConstraint;
	}
	
	public ForeignKeyConstraintBuilder references(String referenceFieldName) {
		this.foreignKeyConstraint.setReferenceFieldName(referenceFieldName);
		return this;
	}
	
	public ForeignKeyConstraintBuilder on(String referenceTableName) {
		this.foreignKeyConstraint.setReferenceTable(referenceTableName);
		return this;
	}
	
	public ForeignKeyConstraint build() {
		return this.foreignKeyConstraint;
	}
	
	private ForeignKeyConstraint foreignKeyConstraint;
}
