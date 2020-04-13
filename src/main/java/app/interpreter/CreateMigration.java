package app.interpreter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * class CreatedMigration:
 *
 * This class is responsible for accepting arguments from the user
 * console inputs after the cm(create-migration) command and it will
 * create the migration file based on these arguments.
 */
class CreateMigration {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public CreateMigration(String args) {
		this.computeDataMembers(args);
		if (dataMembersValid) this.performOperation();
	}
	
	/**
	 * computeDataMembers():
	 * This method will generate all the values required for generating a
	 * migration file. The data members values generated will be targetFileName,
	 * tableName, operation to be performed.
	 * @param args : User console inputs
	 */
	private void computeDataMembers(String args) {
		if (!(args == null || "".equals(args))) {
			this.fileName = this.computeFileName(args);
			if (isNameValid(this.fileName)) {
				this.operation = this.computeOperation(this.fileName).toLowerCase();
				this.tableName = this.computeTableName(args);
				this.targetFileName = this.computeTargetFileName(this.fileName);
				dataMembersValid = true;
			} else System.out.println("-usage: file_name syntax is (Create|Alter)<TableName>CreateTable");
		} else System.out.println("-usage: cm <file_name> [<table_name>]");
	}
	
	/**
	 * computeTargetFileName():
	 * This file will generate the target migration file name by adding the
	 * timestamp to the specified fileName.
	 *
	 * @param fileName : Specified fileName
	 * @return : target migration fileName
	 */
	private String computeTargetFileName(String fileName) {
		return fileName + new Date().getTime();
	}
	
	/**
	 * performOperation():
	 * This method is responsible for generating the target file by taking all
	 * data members and adding them to the predefined template.
	 */
	private void performOperation() {
		try {
			File migrationFile = new File(Commands.MIGRATIONS_DESTINATION + "\\" + this.targetFileName + ".java");
			if (migrationFile.createNewFile()) {

				String template = "\n" +
						"import app.DatabaseMigrator;\n" +
						"import app.database.ColumnBuilder;\n" +
						"import app.database.constraints.ConstraintBuilder;\n" +
						"\n" +
						"public class " + this.targetFileName + " {\n" +
						"\t\n" +
						"\tpublic void up() {\n" +
						"\t\tDatabaseMigrator." + ("create".equalsIgnoreCase(this.operation) ? "create" : "table") + "(\"" + this.tableName + "\", (table) -> {\n" +
						"\t\t\t" + ("create".equalsIgnoreCase(this.operation) ? "table.id(); // Primary Key" : "") + "\n" +
						"\t\t});\n" +
						"\t}\n" +
						"\t\n" +
						"\tpublic void down() {\n" +
						"\t\t" + ("create".equalsIgnoreCase(this.operation)? "DatabaseMigrator.dropIfExists(\"" + this.tableName + "\");" : "") + "\n" +
						"\t}\n" +
						"\t\n" +
						"}\n";
				
				FileOutputStream fileOutputStream = new FileOutputStream(migrationFile);
				fileOutputStream.write(template.getBytes());
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * init():
	 * The method that will create the initial migrations.
	 *
	 * The initial migrations are :
	 * 1. CreateUsersTable
	 */
	public static void init() {
		try {
			File migrationFile = new File(Commands.MIGRATIONS_DESTINATION + "\\CreateUsersTable00000000.java");
			if (migrationFile.createNewFile()) {
				String template = "\n" +
						"import app.DatabaseMigrator;\n" +
						"import app.database.ColumnBuilder;\n" +
						"import app.database.constraints.ConstraintBuilder;\n" +
						"\n" +
						"public class CreateUsersTable00000000 {\n" +
						"\t\n" +
						"\tpublic void up() {\n" +
						"\t\tDatabaseMigrator.create(\"users\", (table) -> {\n" +
						"\t\t\ttable.id(); // Primary Key\n" +
						"\t\t\ttable.addColumn(ColumnBuilder.string(\"name\"));\n" +
						"\t\t\ttable.addColumn(ColumnBuilder.string(\"email\").unique());\n" +
						"\t\t\ttable.addColumn(ColumnBuilder.string(\"password\"));\n" +
						"\t\t});\n" +
						"\t}\n" +
						"\t\n" +
						"\tpublic void down() {\n" +
						"\t\n" +
						"\t}\n" +
						"\t\n" +
						"}\n";
				
				FileOutputStream fileOutputStream = new FileOutputStream(migrationFile);
				fileOutputStream.write(template.getBytes());
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * isNameValid():
	 * A validation method to check whether the fileName entered by user
	 * is of the required syntax or not.
	 *
	 * @param input : User console input
	 * @return : Result of validation
	 */
	private boolean isNameValid(String input) {
		String nameRegex = "^(Create|Alter)(([A-Z][a-z]+)+)Table$";
		return Pattern.compile(nameRegex).matcher(input).matches();
	}
	
	
	/**
	 * computeFileName():
	 * This method is responsible for retrieving the fileName from the
	 * user console input.
	 *
	 * @param input : user console input
	 * @return : fileName as entered by user.
	 */
	private String computeFileName(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return input;
		return input.substring(0, index).trim();
	}
	
	
	/**
	 * computeTableName():
	 * This method is responsible for generating the tableName.
	 * Since there is an option given for adding a custom-table name.
	 * If a table name is specified by the user, it is returned straightaway.
	 * If the table is not mentioned, it is generated into an sql-friendly
	 * format by retrieving it from the file name syntax.
	 *
	 * Eg: I/P: CreateUserAddressesTable
	 * O/P: user-addresses
	 *
	 * @param arguments : User console input
	 * @return : desired table name in sql-friendly format
	 */
	private String computeTableName(String arguments) {
		if (this.isTableNamePresent(arguments)) {
			int index = arguments.indexOf(' ');
			return arguments.substring(index).trim();
		} else {
			String findTableRegex = "^([A-Z][a-z]+?)(([A-Z][a-z]+)+?)Table$";
			Pattern findTablePattern = Pattern.compile(findTableRegex);
			Matcher findTableMatcher = findTablePattern.matcher(this.fileName);
			String tableName;
			if (findTableMatcher.find()) tableName =  findTableMatcher.group(2);
			else throw new IllegalStateException("Migration name entered is weird");
			
			tableName = tableName.substring(0, 1).toLowerCase() + tableName.substring(1);
			for (int i = 0; i<tableName.length(); i++) {
				if (Character.isUpperCase(tableName.charAt(i))) {
					tableName = tableName.substring(0, i) + "_" + tableName.substring(i, i+1).toLowerCase() + tableName.substring(i+1);
				}
			}
			return tableName;
		}
	}
	
	/**
	 * computeOperation():
	 * This method will retrieve the operation to be performed (create or alter)
	 * from the file name specified by the user.
	 *
	 * @param fileName : file name specified by user
	 * @return : operation to be performed
	 */
	private String computeOperation(String fileName) {
		String findOperationRegex = "^([A-Z][a-z]+?)(([A-Z][a-z]+)+?)Table$";
		Pattern findOperationPattern = Pattern.compile(findOperationRegex);
		Matcher findOperationMatcher = findOperationPattern.matcher(fileName);
		if (findOperationMatcher.find()) return findOperationMatcher.group(1);
		else throw new IllegalStateException("Migration name entered is weird");
	}
	
	/**
	 * isTableNamePresent():
	 * Check whether the user has specified table name or not.
	 * @param arguments : User console input
	 * @return : Result whether the table is specified or not.
	 */
	private boolean isTableNamePresent(String arguments) {
		int index = arguments.indexOf(' ');
		return !(index == -1);
	}
	
	String operation;
	String tableName;
	String fileName;
	String targetFileName;
	boolean dataMembersValid = false;
}
