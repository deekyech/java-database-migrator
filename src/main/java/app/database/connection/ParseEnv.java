package app.database.connection;

import app.AppConstants;

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
public class ParseEnv {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public ParseEnv() {
		
		try {
			String envPath = AppConstants.PROJECT_ROOT + "\\src\\main\\resources\\.env";
			envFile = new File(envPath);
			variables = new HashMap<>();
			
			/**
			 * The following code reads all lines from the .env file and for each
			 * line it generates a key=value pair that is entered into the
			 * variables HashMap.
			 */
			Files
					.readAllLines(envFile.toPath())
					.stream()
					.filter(line -> !(line.startsWith("#") || "".equals(line)))
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
	public String getDatabaseVariable(String variable) {
		return variables.get(variable);
	}
	
	
	private HashMap<String, String> variables;
	private File envFile;
}
