package app.database.constraints;

import app.database.Builder;

/**
 * class PrimaryKeyConstraintBuilder:
 * A builder class responsible for building primary key constraints in the table.
 * It has a data member PrimaryKeyConstraint. This class will keep modifying the
 * attributes of the PrimaryKeyConstraint as per migration file. Once the
 * PrimaryKeyConstraint is fully built, it is added to the table.
 * By the use of this class, chaining can be achieved in the migrations.
 * You can notice that all methods of this class return this.
 * This has been done to implement chaining.
 */
class PrimaryKeyConstraintBuilder implements Builder {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public PrimaryKeyConstraintBuilder(PrimaryKeyConstraint primaryKeyConstraint) {
		this.primaryKeyConstraint = primaryKeyConstraint;
	}
	
	public PrimaryKeyConstraint build() {
		return this.primaryKeyConstraint;
	}
	
	private PrimaryKeyConstraint primaryKeyConstraint;
}
