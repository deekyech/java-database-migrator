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
		String s = "UniqueKeyConstraint: ";
		if (this.isMultiColumn()) {
			s += "(MultiColumn) Columns: ";
			System.out.println(s);
			this.getColumnNames().forEach(System.out::println);
		} else {
			s += "(SingleColumn) " + this.getFieldName();
			System.out.println(s);
		}
	}
	
	@Override
	public String getDefinition() {
		String columnDefinition = "UNIQUE (";
		if (this.isMultiColumn()) {
			List<String> columnNames = this.getColumnNames();
			for (int i = 0; i < columnNames.size(); i++) {
				columnDefinition += columnNames.get(i);
				if (i < columnNames.size() - 1) columnDefinition += ", ";
			}
		} else columnDefinition += this.getFieldName();
		columnDefinition += ")";
		return columnDefinition;
	}
}
