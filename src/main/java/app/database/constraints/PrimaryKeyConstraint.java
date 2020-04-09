package app.database.constraints;


/**
 * class PrimaryKeyConstraint:
 * A model class to define all the attributes of a primary key constraint.
 */
class PrimaryKeyConstraint extends Constraint {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public PrimaryKeyConstraint(String fieldName) {
		super(fieldName);
	}
	
	
	/**
	 * printConstraint():
	 * A method used for testing
	 */
	public void printConstraint() {
		System.out.println("\nPrimaryKeyConstraint:\nName: " + this.getFieldName() + "\n");
	}
	
	public String getDefinition() {
		return "PRIMARY KEY (" + this.getFieldName() + ")";
	}
}
