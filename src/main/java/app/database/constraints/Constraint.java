package app.database.constraints;

import app.database.TableEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * abstract class Constraint:
 * An abstract class to define all the attributes of a database constraint.
 */
abstract public class Constraint implements TableEntity {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Constraint(String fieldName) {
		this.fieldName = fieldName;
		this.multiColumn = false;
	}
	
	public Constraint(String[] columnNames) {
		this.columnNames = Arrays.asList(columnNames);
		this.multiColumn = true;
	}
	
	/*********************************************************************
	 * *********************GETTERS and SETTERS***************************
	 *********************************************************************/
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public boolean isMultiColumn() {
		return multiColumn;
	}
	
	public void setMultiColumn(boolean multiColumn) {
		this.multiColumn = multiColumn;
	}
	
	public List<String> getColumnNames() {
		return columnNames;
	}
	
	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	/**
	 * printConstraint():
	 * Method used for app.testing.
	 */
	public abstract void printConstraint();
	
	/**
	 * getDefinition():
	 * Method to be implemented by all TableEntity classes.
	 * Used to provide create definition during query generation.
	 * @return : create definition
	 */
	public abstract String getDefinition();
	
	private String fieldName;
	private boolean multiColumn;
	private List<String> columnNames;
}
