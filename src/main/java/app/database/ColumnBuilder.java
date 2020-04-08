package app.database;

public class ColumnBuilder implements Builder {
	
	private ColumnBuilder(Column column) {
		this.column = column;
	}

	
	static ColumnBuilder id(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "bigint", null, false, false, true, false, new Integer(20)));
	}
	
	public static ColumnBuilder unsignedBigInteger(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "bigint", null, false, false, false, false, new Integer(20)));
	}
	
	public static ColumnBuilder bigIncrements(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "bigint", null, false, false, true, false, new Integer(20)));
	}
	
	public static ColumnBuilder bigInteger(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "bigint", null, false, false, false, true, new Integer(20)));
	}
	
	public static ColumnBuilder binary(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "blob", null, false, false, false, false, null));
	}
	
	public static ColumnBuilder booleanValue(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "boolean", null, false, false, false, false, null));
	}
	
	public static ColumnBuilder integer(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "int", null, false, false, false, true, null));
	}
	
	public static ColumnBuilder increments(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "int", null, false, false, true, false, null));
	}
	
	public static ColumnBuilder unsignedInteger(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "int", null, false, false, false, false, null));
	}
	
	public static ColumnBuilder smallInteger(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "shortint", null, false, false, false, true, null));
	}
	
	public static ColumnBuilder unsignedSmallInteger(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "shortint", null, false, false, false, false, null));
	}
	
	public static ColumnBuilder smallIncrements(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "shortint", null, false, false, true, false, null));
	}
	
	public static ColumnBuilder text(String fieldName) {
		return new ColumnBuilder(new Column(fieldName, "text", null, false, false, false, false, null));
	}
	
	public static ColumnBuilder string(String fieldName) {
		return string(fieldName, 255);
	}
	
	public static ColumnBuilder string(String fieldName, Integer size) {
		return new ColumnBuilder(new Column(fieldName, "varchar", null, false, false, false, false, size));
	}
	
	public ColumnBuilder nullable() {
		this.column.setNullable(true);
		return this;
	}
	
	public ColumnBuilder defaultValue(Object defaultValue) {
		this.column.setDefaultValue(defaultValue);
		return this;
	}
	
	public ColumnBuilder unique() {
		this.column.setUnique(true);
		return this;
	}
	
	public Column build() {
		return this.column;
	}
	
	Column column;
}
