package app.database;

/**
 * class ForeignKeyConstraintBuilder:
 * A builder class responsible for building foreign key constraints in the table.
 * It has a data member ForeignKeyConstraint. This class will keep modifying the
 * attributes of the ForeignKeyConstraint as per migration file. Once the
 * ForeignKeyConstraint is fully built, it is added to the table.
 * By the use of this class, chaining can be achieved in the migrations.
 * You can notice that all methods of this class return this.
 * This has been done to implement chaining.
 */
public class ForeignKeyConstraintBuilder implements Builder {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
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
