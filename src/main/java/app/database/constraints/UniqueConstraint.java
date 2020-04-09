package app.database.constraints;


import java.util.ArrayList;
import java.util.List;

/**
 * class UniqueConstraint:
 * A model class for multiple column unique index implementation.
 */
public class UniqueConstraint extends Constraint {
	
	public UniqueConstraint(String[] fieldNames) {
		super(fieldNames);
	}
	
	public UniqueConstraint(String fieldName) {
		super(fieldName);
	}
	
	@Override
	public void printConstraint() {
	
	}
	
	@Override
	public String getDefinition() {
		String columnDefinition = "UNIQUE (";
		List<String> columnNames = this.getColumnNames();
		for (int i = 0; i<columnNames.size(); i++) {
			columnDefinition += columnNames.get(i);
			if (i < columnNames.size()-1) columnDefinition += ", ";
		}
		columnDefinition += ")";
		return columnDefinition;
	}
}
