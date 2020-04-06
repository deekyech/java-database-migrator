package app.database.connection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;


class ParseEnv {
	
	ParseEnv() {
		
		try {
			envFile = new File("../../../resources/.env");
			
			Files
					.readAllLines(envFile.toPath())
					.forEach((line) -> variables.put(line.split("=")[0], line.split("=")[1]));
			
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	String getDatabaseVariable(String variable) {
		return variables.get(variable);
	}
	
	
	private HashMap<String, String> variables;
	private File envFile;
}
