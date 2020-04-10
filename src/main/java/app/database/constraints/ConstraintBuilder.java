package app.database.constraints;

import app.database.Builder;

/**
 * class ConstraintBuilder:
 *
 * A constraint builder abstract class that initialises objects of its
 * subclasses. Its Subclasses will be the builders of all SQLConstraint
 * classes.
 */
public abstract class ConstraintBuilder implements Builder {
	
	
	/**
	 * foreign():
	 * A method that supplies the ForeignKeyConstraintBuilder class for
	 * foreign key references in the database.
	 *
	 * @param fieldName : Column of the database that is to be defined as
	 *                    foreign key
	 * @return : ForeignKeyConstraintBuilder object for further chaining and
	 *          Building.
	 */
	public static ForeignKeyConstraintBuilder foreign(String fieldName) {
		return new ForeignKeyConstraintBuilder(new ForeignKeyConstraint(fieldName));
	}
	
	
	/**
	 * foreign():
	 * A method that supplies the PrimaryKeyConstraintBuilder class for
	 * primary key declarations in the database.
	 * @param fieldName : Column of the database that is to be defined as
 * 	 *                    primary key
	 * @return : PrimaryKeyConstraintBuilder object.
	 */
	public static PrimaryKeyConstraintBuilder primary(String fieldName) {
		return new PrimaryKeyConstraintBuilder(new PrimaryKeyConstraint(fieldName));
	}
	
	/**
	 * unique():
	 * A method that supplies the UniqueConstraintBuilder object for
	 * multi-column unique constraint.
	 * @param columnNames : All column names to which unique constraints
	 *                      are to be applied.
	 * @return :UniqueConstraintBuilder object.
	 */
	public static UniqueConstraintBuilder unique(String[] columnNames) {
		return new UniqueConstraintBuilder(new UniqueConstraint(columnNames));
	}
	
	/**
	 * unique():
	 * A method that supplies the UniqueConstraintBuilder object for
	 * a single column unique constraint.
	 * @param columnName : Column name that must be made unique.
	 * @return : UniqueConstraintBuilder object.
	 */
	public static UniqueConstraintBuilder unique(String columnName) {
		return new UniqueConstraintBuilder(new UniqueConstraint(columnName));
	}
	
	abstract public Constraint build();
	
}
