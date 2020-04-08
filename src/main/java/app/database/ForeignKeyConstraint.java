package app.database;

class ForeignKeyConstraint extends Constraint {
	
	public ForeignKeyConstraint(String fieldName) {
		super(fieldName);
	}
	
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
	
	public void printConstraint() {
		System.out.println("\nForeignKeyConstraint:\nName: " + this.getFieldName() + "\nReference Field Name: " + this.getReferenceFieldName() + "\nReference Table: " + this.getReferenceTable());
	}
	
	private String referenceFieldName;
	private String referenceTable;
}
