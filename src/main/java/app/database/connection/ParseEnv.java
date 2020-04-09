package app.database.connection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * class ParseEnv:
 * A class to parse the .env file and read all variables required for
 * operations on the database.
 *
 */
class ParseEnv {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	ParseEnv() {
		
		try {
			envFile = new File("D:\\Dhiresh\\Drive Sync\\Coding\\Java\\Java Projects\\JavaDatabaseMigrator\\src\\main\\resources\\.env");
			variables = new HashMap<>();
			
			/**
			 * The following code reads all lines from the .env file and for each
			 * line it generates a key=value pair that is entered into the
			 * variables HashMap.
			 */
			Files
					.readAllLines(envFile.toPath())
					.forEach((line) -> {
						if (!("".equals(line) || line == null)) variables.put(line.split("=")[0], line.split("=")[1]);
					});
			
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * getDatabaseVariable():
	 * This method returns the variable from the variables HashMap required
	 * by the program for performing any database operation.
	 *
	 * @param variable : Variable key
	 * @return : Variable value
	 */
	String getDatabaseVariable(String variable) {
		return variables.get(variable);
	}
	
	
	private HashMap<String, String> variables;
	private File envFile;
}
