package app.database;

import app.database.queries.Query;

import java.util.ArrayList;
import java.util.List;

public class AlterTable extends Table {
	
	public AlterTable(String tableName) {
		super(tableName);
		dropColumnList = new ArrayList<>();
	}
	
	
	@Override
	public void dropColumn(String columnName) {
		this.dropColumnList.add(columnName);
	}
	
	@Override
	public void dropConstraint(String constraintName) {
	
	}
	
	@Override
	public Query toQuery() {
		StringBuffer query = new StringBuffer("ALTER TABLE " + tableName + " ");
		this.columns.forEach(column -> query.append("ADD COLUMN " + column.getDefinition() + ", "));
		this.constraints.forEach(constraint -> query.append("ADD " + constraint.getDefinition() + ", "));
		this.dropColumnList.forEach(column -> query.append("DROP " + column + ", "));
		// Remove the last ,
		String queryString = query.substring(0, query.length()-2);
		return new Query(queryString);
	}
	
	
	private List<String> dropColumnList;
}

