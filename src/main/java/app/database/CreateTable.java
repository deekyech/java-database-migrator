package app.database;

import app.database.constraints.Constraint;
import app.database.constraints.ConstraintBuilder;
import app.database.queries.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * class CreateTable:
 * A model class that stores all the attributes of a database tableName.
 */
public class CreateTable extends Table {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public CreateTable(String table) {
		super(table);
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
	 * printTable():
	 * Created for app.testing purposes
	 */
	public void printTable() {
		System.out.println("CreateTable " + this.tableName + "\n\nColumns List:\n");
		columns.forEach(column -> column.printColumn());
		System.out.println("\n\nConstraints List:\n");
		constraints.forEach(constraint -> constraint.printConstraint());
	}
	
	@Override
	/**
	 * toQuery():
	 * This method returns the query for the object's structure.
	 * @return
	 */
	public Query toQuery() {
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
	
	
	
}
