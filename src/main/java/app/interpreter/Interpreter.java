package app.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *
 *  class Interpreter:
 *
 *  The class that will execute the interpreter for all user command inputs.
 */
public class Interpreter {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	
	public Interpreter() {
		this.run();
	}
	
	/**
	 * run():
	 * The method that will accept the user input commands.
	 */
	private void run() {
		CreateMigration.init();
		while (true) {
			System.out.print("> ");
			String input = this.acceptInput();
			if (input == null) continue;
			String command = this.getCommand(input);
			if (this.isCommand(command)) {
				if (Commands.CREATE_MIGRATION_COMMAND.equals(command)) {
					new CreateMigration(getArguments(input));
				} else if (Commands.MIGRATE_COMMAND.equals(command)) {
					new Migrate();
				} else if (Commands.EXIT_COMMAND.equals(command)) {
					System.exit(-1);
				} else if (Commands.HELP_COMMAND.equals(command)) {
					System.out.println("Help: You can use the following commands:");
					Arrays.asList(Commands.ALL_COMMANDS).forEach(System.out::println);
				}
			} else System.out.println("'" + command + "' is not a valid command. You may use the command 'help' to view all commands.");
		}
	}
	
	/**
	 * acceptInput():
	 * This method will read the input from the user console.
	 * @return : user command input
	 */
	private String acceptInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *
	 * isCommand():
	 * This method will check whether the specified command is a valid command or not.
	 *
	 * @param command : The command that needs to be checked.
	 * @return : Will return the result whether the specified command is a valid command or not.
	 */
	private boolean isCommand(String command) {
		for (int i = 0; i<Commands.ALL_COMMANDS.length; i++) {
			if (Commands.ALL_COMMANDS[i].equals(command)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * getCommand():
	 * This method will retrieve the command(first word) from the whole user input.
	 *
	 * @param input : User console input
	 * @return : Command
	 */
	private String getCommand(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return input;
		return input.substring(0, index).trim();
	}
	
	
	/**
	 * getArguments():
	 * This method will retrieve the arguments for the specified commands from the whole user input.
	 *
	 * @param input : User console input
	 * @return : Argument string
	 */
	private String getArguments(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return null;
		return input.substring(index).trim();
	}
}
