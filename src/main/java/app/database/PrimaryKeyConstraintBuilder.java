package app.database;

class PrimaryKeyConstraintBuilder implements Builder {
	
	public PrimaryKeyConstraintBuilder(PrimaryKeyConstraint primaryKeyConstraint) {
		this.primaryKeyConstraint = primaryKeyConstraint;
	}
	
	public PrimaryKeyConstraint build() {
		return this.primaryKeyConstraint;
	}
	
	private PrimaryKeyConstraint primaryKeyConstraint;
}
