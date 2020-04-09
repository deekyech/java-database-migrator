package app.database;

/**
 * abstract class Constraint:
 * An abstract class to define all the attributes of a database constraint.
 */
abstract class Constraint implements TableEntity {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Constraint(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	/*********************************************************************
	 * *********************GETTERS and SETTERS***************************
	 *********************************************************************/
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	/**
	 * printConstraint():
	 * Method used for testing.
	 */
	public abstract void printConstraint();
	
	/**
	 * getDefinition():
	 * Method to be implemented by all TableEntity classes.
	 * Used to provide create definition during query generation.
	 * @return : create definition
	 */
	public abstract String getDefinition();
	
	private String fieldName;
}
