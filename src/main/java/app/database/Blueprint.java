package app.database;

import java.util.List;

public class Blueprint {
	
	public Blueprint(String table) {
		this.table = table;
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
	
	private String table;
	private List<Column> columns;
}
