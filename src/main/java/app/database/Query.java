package app.database;

/**
 * class Query:
 * A model class to represent a query.
 */
public class Query {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Query(String query) {
		this.query = query;
	}
	
	
	/*********************************************************************
	 * *********************GETTERS AND SETTERS***************************
	 *********************************************************************/
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String toString() {
		return this.query;
	}
	
	private String query;
}
