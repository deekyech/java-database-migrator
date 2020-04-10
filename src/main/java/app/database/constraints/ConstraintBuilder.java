package app.database.constraints;

import app.database.Builder;

/**
 * class ConstraintBuilder:
 *
 * Not actually a Builder class.
 * It is more of a supplier class. It supplies the type of ConstraintBuilder
 * class needed. (PrimaryKeyConstraintBuilder & ForeignKeyConstraintBuilder).
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
	
	
	public static UniqueConstraintBuilder unique(String[] columnNames) {
		return new UniqueConstraintBuilder(new UniqueConstraint(columnNames));
	}
	
	public static UniqueConstraintBuilder unique(String columnName) {
		return new UniqueConstraintBuilder(new UniqueConstraint(columnName));
	}
	
	abstract public Constraint build();
	
}
