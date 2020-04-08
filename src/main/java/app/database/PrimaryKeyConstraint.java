package app.database;

class PrimaryKeyConstraint extends Constraint {
	
	public PrimaryKeyConstraint(String fieldName) {
		super(fieldName);
	}
	
	public void printConstraint() {
		System.out.println("\nPrimaryKeyConstraint:\nName: " + this.getFieldName() + "\n");
	}
}
