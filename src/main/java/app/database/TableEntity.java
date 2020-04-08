package app.database;

/**
 * interface TableEntity:
 * A marker interface to mark the objects that can be an attribute of a
 * database table.
 */
public interface TableEntity {
	String getDefinition();
}
