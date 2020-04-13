package app.database.constraints;


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
	
	public String getOnDeleteOption() {
		return onDeleteOption;
	}
	
	public void setOnDeleteOption(String onDeleteOption) {
		this.onDeleteOption = onDeleteOption;
	}
	
	public String getOnUpdateOption() {
		return onUpdateOption;
	}
	
	public void setOnUpdateOption(String onUpdateOption) {
		this.onUpdateOption = onUpdateOption;
	}
	
	/**
	 * printConstraint():
	 * A method used for app.testing
	 */
	public void printConstraint() {
		System.out.println("\nForeignKeyConstraint:\nName: " + this.getFieldName() + "\nReference Field Name: " + this.getReferenceFieldName() + "\nReference CreateTable: " + this.getReferenceTable());
	}
	
	public String getDefinition() {
		String constraintDefinition = "FOREIGN KEY (" + this.getFieldName() + ") REFERENCES " + this.getReferenceTable() + "(" + this.getReferenceFieldName() + ")";
		if (this.getOnDeleteOption() != null) constraintDefinition = constraintDefinition + " ON DELETE " + this.getOnDeleteOption();
		if (this.getOnUpdateOption() != null) constraintDefinition = constraintDefinition + " ON UPDATE " + this.getOnUpdateOption();
		return constraintDefinition;
	}
	
	private String referenceFieldName;
	private String referenceTable;
	private String onDeleteOption;
	private String onUpdateOption;
}
