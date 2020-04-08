package app.database;

/**
 * interface Builder:
 * An interface to define all the builder classes in the database package.
 * The build() method must be implemented by all Builder classes so that
 * the respective TableEntity can be added to the table.
 *
 */
public interface Builder {
	TableEntity build();
}
