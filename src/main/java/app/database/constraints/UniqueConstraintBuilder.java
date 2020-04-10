package app.database.constraints;

import app.database.Builder;
import app.database.TableEntity;

public class UniqueConstraintBuilder extends ConstraintBuilder {
	
	public UniqueConstraintBuilder(UniqueConstraint uniqueConstraint) {
		this.uniqueConstraint = uniqueConstraint;
	}
	
	@Override
	public UniqueConstraint build() {
		return this.uniqueConstraint;
	}
	
	private UniqueConstraint uniqueConstraint;
}