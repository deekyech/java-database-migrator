package app.database;

import java.util.ArrayList;
import java.util.List;


/**
 * class Table:
 * A model class that stores all the attributes of a database table.
 */
public class Table {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Table(String table) {
		this.table = table;
		columns = new ArrayList<>();
		constraints = new ArrayList<>();
	}
	
	/**
	 * id():
	 * This method is used to define the id column as the primary key of
	 * this table.
	 */
	public void id() {
		this.id("id");
	}
	
	public void id(String fieldName) {
		this.addColumn(ColumnBuilder.id(fieldName));
	}
	
	/**
	 * addColumn():
	 * Method that will be called from the migration file to add a column to
	 * the table.
	 * @param builder : ColumnBuilder object of the column that is to be inserted
	 */
	public void addColumn(ColumnBuilder builder) {
		this.columns.add(builder.build());
	}
	
	/**
	 * addConstraint():
	 * Method that will be called from the migration file to add a constraint to
	 * the table.
	 *
	 * @param builder : Builder object of the constraint that is to be inserted.
	 * @throws Exception
	 */
	public void addConstraint(Builder builder) throws Exception {
		TableEntity entity = builder.build();
		if (entity instanceof ForeignKeyConstraint) {
			ForeignKeyConstraint foreignKeyConstraint = (ForeignKeyConstraint) entity;
			
			//Validations
			if (columnExists(foreignKeyConstraint.getFieldName())) {
				if (referenceTableExists(foreignKeyConstraint.getReferenceTable())) {
					if (referenceColumnExists(foreignKeyConstraint.getReferenceFieldName())) {
						if (isReferenceColumnPrimaryKey(foreignKeyConstraint.getReferenceFieldName())) {
							this.constraints.add(foreignKeyConstraint);
						} else {
							throw new Exception("Reference table column exists but that column is not a primary key");
						}
					} else {
						throw new Exception("Reference table column not found.");
					}
				} else {
					throw new Exception("Reference table does not exist.");
				}
			} else {
				throw new Exception("Specified column does not exist.");
			}
		} else {
			PrimaryKeyConstraint primaryKeyConstraint = (PrimaryKeyConstraint) entity;
			if (columnExists(primaryKeyConstraint.getFieldName())) {
				this.constraints.add(primaryKeyConstraint);
			} else {
				throw new Exception("Specified column does not exist.");
			}
		}
	}
	
	/**
	 * isReferenceColumnPrimaryKey():
	 * referenceColumnExists():
	 * referenceTableExists():
	 * columnExists():
	 * Methods used for validations of foreign key references.
	 * Not implemented for now. Will ask sir how it is done by laravel.
	 * @param referenceFieldName
	 * @return
	 */
	private boolean isReferenceColumnPrimaryKey(String referenceFieldName) {
		return true;
	}
	
	private boolean referenceColumnExists(String referenceFieldName) {
		return true;
	}
	
	private boolean referenceTableExists(String referenceTable) {
		return true;
	}
	
	private boolean columnExists(String fieldName) {
		return true;
	}
	
	/**
	 * printTable():
	 * Created for testing purposes
	 */
	public void printTable() {
		System.out.println("Table " + table + "\n\nColumns List:\n");
		columns.forEach(column -> column.printColumn());
		System.out.println("\n\nConstraints List:\n");
		constraints.forEach(constraint -> constraint.printConstraint());
	}
	
	private String table;
	private List<Column> columns;
	private List<Constraint> constraints;
}
