package app.database;

import app.database.constraints.Constraint;
import app.database.constraints.ConstraintBuilder;
import app.database.queries.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * class Table:
 * A model class that stores all the attributes of a database tableName.
 */
public class Table {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Table(String table) {
		this.tableName = table;
		columns = new ArrayList<>();
		constraints = new ArrayList<>();
	}
	
	/**
	 * id():
	 * This method is used to define the id column as the primary key of
	 * this tableName.
	 */
	public void id() {
		this.id("id");
	}
	
	public void id(String fieldName) {
		this.addColumn(ColumnBuilder.id(fieldName));
		try {
			this.addConstraint(ConstraintBuilder.primary(fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * addColumn():
	 * Method that will be called from the migration file to add a column to
	 * the tableName.
	 * @param builder : ColumnBuilder object of the column that is to be inserted
	 */
	public void addColumn(ColumnBuilder builder) {
		this.columns.add(builder.build());
	}
	
	/**
	 * addConstraint():
	 * Method that will be called from the migration file to add a constraint to
	 * the tableName.
	 *
	 * @param builder : Builder object of the constraint that is to be inserted.
	 * @throws Exception
	 */
	public void addConstraint(ConstraintBuilder builder) {
		TableEntity entity = builder.build();
		Constraint constraint = (Constraint) entity;
		if (!constraint.isMultiColumn()) {
			if (this.columnExists(constraint.getFieldName())) {
				if (!this.constraintExists(constraint)) {
					this.constraints.add(constraint);
				} else {
					System.out.println("Constraint already exists on specified column.");
				}
			} else {
				System.out.println("Specified column does not exist.");
			}
		} else {
			List<String> columnList = constraint.getColumnNames();
			for (String column: columnList) {
				if (this.columnExists(column)) {
					if (!this.constraintExists(column)) {
					
					} else {
						System.out.println("Constraint already exists on specified column.");
					}
				} else {
					System.out.println("Specified column does not exist.");
				}
			}
			this.constraints.add(constraint);
		}
	}
	
	private boolean constraintExists(Constraint newConstraint) {
		Iterator<Constraint> iterator = this.constraints.iterator();
		while(iterator.hasNext()) {
			Constraint constraint = iterator.next();
			if (constraint.getFieldName().equals(newConstraint.getFieldName()) && constraint.getClass() == newConstraint.getClass()) return true;
		}
		return false;
	}
	
	/**
	 * constraintExists():
	 * Method to verify that no duplicate constraints are being inserted.
	 * @param fieldName
	 * @return
	 */
	private boolean constraintExists(String fieldName) {
		Iterator<Constraint> iterator = this.constraints.iterator();
		while(iterator.hasNext()) {
			Constraint constraint = iterator.next();
			if (constraint.getFieldName().equals(fieldName)) return true;
		}
		return false;
	}
	
	/**
	 * columnExists():
	 * Methods used for validations of key references.
	 * @param fieldName
	 * @return
	 */
	private boolean columnExists(String fieldName) {
		Iterator<Column> iterator = this.columns.iterator();
		while(iterator.hasNext()) {
			Column column = iterator.next();
			if (column.getName().equals(fieldName)) return true;
		}
		return false;
	}
	
	/**
	 * printTable():
	 * Created for app.testing purposes
	 */
	public void printTable() {
		System.out.println("Table " + tableName + "\n\nColumns List:\n");
		columns.forEach(column -> column.printColumn());
		System.out.println("\n\nConstraints List:\n");
		constraints.forEach(constraint -> constraint.printConstraint());
	}
	
	/**
	 * toCreateQuery():
	 * This method returns the query for the object's structure.
	 * @return
	 */
	public Query toCreateQuery() {
		StringBuffer query = new StringBuffer("CREATE TABLE IF NOT EXISTS " + this.tableName + "(");
		
		for (int i = 0; i<columns.size(); i++) {
			query.append(columns.get(i).getDefinition());
			if (!(i == columns.size()-1)) query.append(", ");
		}
		if (constraints.size()>0) query.append(", ");
		for (int i = 0; i<constraints.size(); i++) {
			query.append(constraints.get(i).getDefinition());
			if (!(i == constraints.size()-1)) query.append(", ");
		}
		
		query.append(")");
		return new Query(query.toString());
	}
	
	public Query toAlterQuery() {
		// To be implemented
		return null;
	}
	
	
	private String tableName;
	private List<Column> columns;
	private List<Constraint> constraints;
}
