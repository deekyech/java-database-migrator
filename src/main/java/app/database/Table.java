package app.database;

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
							throw new Exception("Reference tableName column exists but that column is not a primary key");
						}
					} else {
						throw new Exception("Reference tableName column not found.");
					}
				} else {
					throw new Exception("Reference tableName does not exist.");
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
		Iterator<Column> iterator = this.columns.iterator();
		while(iterator.hasNext()) {
			Column column = iterator.next();
			if (column.getName().equals(fieldName)) return true;
		}
		return false;
	}
	
	/**
	 * printTable():
	 * Created for testing purposes
	 */
	public void printTable() {
		System.out.println("Table " + tableName + "\n\nColumns List:\n");
		columns.forEach(column -> column.printColumn());
		System.out.println("\n\nConstraints List:\n");
		constraints.forEach(constraint -> constraint.printConstraint());
	}
	
	
	public Query toQuery() {
		StringBuffer query = new StringBuffer("CREATE TABLE IF NOT EXISTS " + this.tableName + "(");
		
		for (Column c: columns) {
			query.append(c.getDefinition() + ", ");
		}
		for (int i = 0; i<constraints.size(); i++) {
			query.append(constraints.get(i).getDefinition());
			if (!(i == constraints.size()-1)) query.append(", ");
		}
		
		query.append(")");
		return new Query(query.toString());
	}
	
	private String tableName;
	private List<Column> columns;
	private List<Constraint> constraints;
}
