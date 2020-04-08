package app.database;

/**
 * class Column:
 * A model class to store all the attributes of a database column.
 */
public class Column implements TableEntity {
	
	/*********************************************************************
	 * *************************CONSTRUCTORS******************************
	 *********************************************************************/
	public Column() {}
	
	public Column(String name, String datatype, Object defaultValue, boolean nullable, boolean unique, boolean autoIncrement, boolean signed, Integer size) {
		this.name = name;
		this.datatype = datatype;
		this.defaultValue = defaultValue;
		this.nullable = nullable;
		this.unique = unique;
		this.autoIncrement = autoIncrement;
		this.signed = signed;
		this.size = size;
	}
	
	/*********************************************************************
	 * **********************Getters and Setters**************************
	 *********************************************************************/
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDatatype() {
		return datatype;
	}
	
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public boolean isNullable() {
		return nullable;
	}
	
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	public boolean isUnique() {
		return unique;
	}
	
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
	public boolean isSigned() {
		return signed;
	}
	
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public void printColumn() {
		System.out.println("\nColumn:\nName: " + this.getName() + "\nDatatype: " + this.getDatatype() + "\nDefaultValue: " + this.getDefaultValue() + "\nNullable: " + this.isNullable() + "\nUnique: " + this.isUnique() + "\nAutoIncrement: " + this.isAutoIncrement() + "\nSigned: " + isSigned() + "\nSize: " + this.getSize());
	}
	
	public String getDefinition() {
		String columnDefinition = this.name + " " + this.datatype;
		if (this.size != null) {
			columnDefinition = columnDefinition + "(" + this.size + ")";
		}
		if (!this.isSigned()) {
			columnDefinition += " UNSIGNED";
		}
		if (!this.isNullable()) {
			columnDefinition += " NOT NULL";
		}
		if (this.isUnique()) {
			columnDefinition += " UNIQUE";
		}
		if (this.isAutoIncrement()) {
			columnDefinition += " AUTO INCREMENT";
		}
		if (this.defaultValue != null) {
			columnDefinition = columnDefinition + " DEFAULT '" + this.defaultValue + "'";
		}
		return columnDefinition;
	}
	
	private String name;
	private String datatype;
	private Object defaultValue;
	private boolean nullable;
	private boolean unique;
	private boolean autoIncrement;
	private boolean signed;
	private Integer size;
}
