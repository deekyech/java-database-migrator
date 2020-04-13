package app;

import app.database.connection.ParseEnv;

public interface AppConstants {
	String PROJECT_ROOT = System.getProperty("user.dir");
	String APP_ROOT = PROJECT_ROOT + "\\src\\main\\java\\app";
	//Migrations folder path
//	String MIGRATIONS_DESTINATION = AppConstants.APP_ROOT + "\\migrations";
	String MIGRATIONS_DESTINATION = new ParseEnv().getDatabaseVariable("MIGRATIONS_DESTINATION");
}
