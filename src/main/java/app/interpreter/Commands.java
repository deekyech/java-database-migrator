package app.interpreter;


import app.AppConstants;

/**
 * interface Commands:
 * An interface to store the constants of all commands.
 */
public interface Commands {
	
	
	//Commands
	String CREATE_MIGRATION_COMMAND = "cm";
	String HELP_COMMAND = "help";
	String MIGRATE_COMMAND = "migrate";
	String EXIT_COMMAND = "exit";
	
	//Commands list
	String[] ALL_COMMANDS = new String[]{CREATE_MIGRATION_COMMAND, HELP_COMMAND, MIGRATE_COMMAND, EXIT_COMMAND};
}