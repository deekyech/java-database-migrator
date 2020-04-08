package app.interpreter;


/**
 * interface Commands:
 * An interface to store the constants of all commands.
 */
public interface Commands {
	String CREATE_MIGRATION_COMMAND = "cm";
	String HELP_COMMAND = "help";
	String MIGRATE_COMMAND = "migrate";
	String[] ALL_COMMANDS = new String[]{CREATE_MIGRATION_COMMAND, HELP_COMMAND, MIGRATE_COMMAND};
}