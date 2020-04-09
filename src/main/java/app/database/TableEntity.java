package app.database;

/**
 * interface TableEntity:
 * An interface to mark TableEntities.
 */
public interface TableEntity {
	
	/**
	 * getDefinition():
	 * This method will convert the respective TableEntity object into
	 * its equivalent create definition for query generation.
	 * @return : create definition
	 */
	String getDefinition();
}
