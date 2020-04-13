package app.database;

import app.database.constraints.Constraint;
import app.database.constraints.ConstraintBuilder;
import app.database.queries.Query;

import java.util.ArrayList;
import java.util.List;

public class AlterTable extends Table {
	
	public AlterTable(String tableName) {
		super(tableName);
		dropColumnList = new ArrayList<>();
	}
	
	
	@Override
	public void addConstraint(ConstraintBuilder constraintBuilder) {
		this.constraints.add(constraintBuilder.build());
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
		String template = "ALTER TABLE " + tableName + " ";
		return null;
	}
	
	
	private List<String> dropColumnList;
//	private List<String>
}

