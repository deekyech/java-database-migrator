package app.database;


/**
 * class ForeignKeyConstraint:
 * A model class to define all the attributes of a foreign key constraint.
 */
class ForeignKeyConstraint extends Constraint {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public ForeignKeyConstraint(String fieldName) {
		super(fieldName);
	}
	
	/*********************************************************************
	 * *********************GETTERS and SETTERS***************************
	 *********************************************************************/
	public String getReferenceFieldName() {
		return referenceFieldName;
	}
	
	public void setReferenceFieldName(String referenceFieldName) {
		this.referenceFieldName = referenceFieldName;
	}
	
	public String getReferenceTable() {
		return referenceTable;
	}
	
	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}
	
	/**
	 * printConstraint():
	 * A method used for testing
	 */
	public void printConstraint() {
		System.out.println("\nForeignKeyConstraint:\nName: " + this.getFieldName() + "\nReference Field Name: " + this.getReferenceFieldName() + "\nReference Table: " + this.getReferenceTable());
	}
	
	private String referenceFieldName;
	private String referenceTable;
}
