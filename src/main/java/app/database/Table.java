package app.database;

import app.database.constraints.Constraint;
import app.database.constraints.ConstraintBuilder;
import app.database.queries.Query;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
	
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
	
	
	abstract public void addConstraint(ConstraintBuilder constraintBuilder);
	
	public void dropColumn(String columnName) {}
	public void dropConstraint(String constraintName) {}
	abstract public Query toQuery();
	
	protected String tableName;
	protected List<Column> columns;
	protected List<Constraint> constraints;
}
