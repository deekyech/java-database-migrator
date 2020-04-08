package app.database;

public class Query {
	
	public Query(String query) {
		this.query = query;
	}
	
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
