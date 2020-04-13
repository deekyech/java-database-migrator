package app.database;

import app.database.constraints.Constraint;
import app.database.constraints.ConstraintBuilder;
import app.database.queries.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * class Table:
 * An abstract class that srves as a blueprint for Create and Alter
 * migration tables.
 */
public abstract class Table {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Table(String tableName) {
		this.tableName = tableName;
		columns = new ArrayList<>();
		constraints = new ArrayList<>();
	}
	
	/**
	 * addColumn():
	 * Method that will be called from the migration file to add a column to
	 * the tableName.
	 * @param columnBuilder : ColumnBuilder object of the column that is to be inserted
	 */
	public void addColumn(ColumnBuilder columnBuilder) {
		this.columns.add(columnBuilder.build());
	}
	
	
	/**
	 * addConstraint():
	 * Method that will be called from the migration file to add a constraint to
	 * the tableName.
	 *
	 * @param constraintBuilder : Builder object of the constraint that is to be inserted.
	 * @throws Exception
	 */
	public void addConstraint(ConstraintBuilder constraintBuilder) {
		this.constraints.add(constraintBuilder.build());
	}
	
	/**
	 * id(): To be overridden by the CreateTable class since
	 * this feature is only given while create migration.
	 */
	public void id() {}
	public void id(String columnName) {}
	
	/**
	 * dropColumn() && dropConstraint():
	 * To be overridden by the AlterTable class since
	 * this feature is only given for alter migration.
	 * @param columnName
	 */
	public void dropColumn(String columnName) {}
	public void dropConstraint(String constraintName) {}
	
	/**
	 * toQuery():
	 * Converts the table definition to SQL query.
	 * @return
	 */
	abstract public Query toQuery();
	
	protected String tableName;
	protected List<Column> columns;
	protected List<Constraint> constraints;
}