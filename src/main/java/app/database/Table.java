package app.database;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	public Table(String table) {
		this.table = table;
		columns = new ArrayList<>();
		constraints = new ArrayList<>();
	}
	
	public void id() {
		this.id("id");
	}
	
	public void id(String fieldName) {
		this.addColumn(ColumnBuilder.id(fieldName));
	}
	
	public void addColumn(ColumnBuilder builder) {
		this.columns.add(builder.build());
	}
	
	public void addConstraint(Builder builder) throws Exception {
		TableEntity entity = builder.build();
		if (entity instanceof ForeignKeyConstraint) {
			ForeignKeyConstraint foreignKeyConstraint = (ForeignKeyConstraint) entity;
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
