package app.interpreter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreateMigration {
	public CreateMigration(String args) {
		this.computeDataMembers(args);
		this.performOperation();
	}
	
	private void computeDataMembers(String args) {
		if (!(args == null || "".equals(args))) {
			this.fileName = this.computeFileName(args);
			if (isNameValid(this.fileName)) {
				this.operation = this.computeOperation(this.fileName).toLowerCase();
				this.tableName = this.computeTableName(args);
				this.targetFileName = this.computeTargetFileName(this.fileName);
			} else System.out.println("-usage: file_name syntax is (Create|Alter)<TableName>Table");
		} else System.out.println("-usage: cm <file_name> [<table_name>]");
	}
	
	private String computeTargetFileName(String fileName) {
		return fileName + new Date().getTime();
	}
	
	private void performOperation() {
		try {
			File migrationFile = new File("D:\\Dhiresh\\Drive Sync\\Coding\\Java\\Java Projects\\JavaDatabaseMigrator\\src\\main\\java\\app\\migrations\\" + this.targetFileName + ".java");
			if (migrationFile.createNewFile()) {
				String template = "package app.migrations;\n" +
						"\n" +
						"import app.DatabaseMigrator;\n" +
						"import app.database.ColumnBuilder;\n" +
						"import app.database.ConstraintBuilder;\n" +
						"\n" +
						"public class " + this.targetFileName + " {\n" +
						"\tpublic static void main(String[] args) {\n" +
						"\t\tDatabaseMigrator." + this.operation.toLowerCase() + "(\"" + this.tableName + "\", (table) -> {\n" +
						"\t\t\ttable.id();\t\n" +
						"\n" +
						"\t\t});\n" +
						"\t}\n" +
						"}";
				
				FileOutputStream fileOutputStream = new FileOutputStream(migrationFile);
				fileOutputStream.write(template.getBytes());
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isNameValid(String input) {
		String nameRegex = "^(Create|Alter)(([A-Z][a-z]+)+)Table$";
		return Pattern.compile(nameRegex).matcher(input).matches();
	}
	
	private String computeFileName(String input) {
		int index = input.indexOf(' ');
		if (index == -1) return input;
		return input.substring(0, index).trim();
	}
	
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
					tableName = tableName.substring(0, i) + "-" + tableName.substring(i, i+1).toLowerCase() + tableName.substring(i+1);
				}
			}
			return tableName;
		}
	}
	
	private String computeOperation(String fileName) {
		String findOperationRegex = "^([A-Z][a-z]+?)(([A-Z][a-z]+)+?)Table$";
		Pattern findOperationPattern = Pattern.compile(findOperationRegex);
		Matcher findOperationMatcher = findOperationPattern.matcher(fileName);
		if (findOperationMatcher.find()) return findOperationMatcher.group(1);
		else throw new IllegalStateException("Migration name entered is weird");
	}
	
	private boolean isTableNamePresent(String arguments) {
		int index = arguments.indexOf(' ');
		return !(index == -1);
	}
	
	String operation;
	String tableName;
	String fileName;
	String targetFileName;
}
