package app.database;

class Column {
	
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
	
	private String name;
	private String datatype;
	private Object defaultValue;
	private boolean nullable;
	private boolean unique;
	private boolean autoIncrement;
	private boolean signed;
	private Integer size;
}
