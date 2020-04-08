package app.database;

abstract class Constraint implements TableEntity {
	
	public Constraint(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public abstract void printConstraint();
	
	private String fieldName;
}
