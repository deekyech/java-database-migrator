package app.database.constraints;

import app.database.Builder;
import app.database.TableEntity;

public class UniqueConstraintBuilder implements Builder {
	
	public UniqueConstraintBuilder(UniqueConstraint uniqueConstraint) {
		this.uniqueConstraint = uniqueConstraint;
	}
	
	@Override
	public TableEntity build() {
		return this.uniqueConstraint;
	}
	
	private UniqueConstraint uniqueConstraint;
}