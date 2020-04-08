package app.database;

abstract class Constraint implements BlueprintEntity {
	
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
