package app.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interpreter {
	public Interpreter() {
		this.run();
	}
	
	private void run() {
		while (true) {
			System.out.print("> ");
			String input = this.acceptInput();
			if (input == null) continue;
			String command = this.getCommand(input);
			if (this.isCommand(command)) {
				if (Commands.CREATE_MIGRATION_COMMAND.equals(command)) {
					new CreateMigration(getArguments(input));
				}
			} else System.out.println("'" + command + "' is not a valid command. You may use the command 'help' to view all commands.");
		}
	}
	
	private String acceptInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean isCommand(String command) {
		for (int i = 0; i<Commands.ALL_COMMANDS.length; i++) {
			if (Commands.ALL_COMMANDS[i].equals(command)) {
				return true;
			}
		}
		return false;
	}
	
	private String getCommand(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return input;
		return input.substring(0, index).trim();
	}
	
	private String getArguments(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return null;
		return input.substring(index).trim();
	}
}
