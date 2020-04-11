package app.interpreter;


import javax.tools.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * class Migrate:
 * The class responsible for the execution of all migrations.
 * Migration files are compiled first and the up() method is invoked.
 */
public class Migrate {
	
	/*********************************************************************
	 * *************************CONSTRUCTOR*******************************
	 *********************************************************************/
	public Migrate() {
		migrate();
	}
	
	
	/**
	 * migrate():
	 * The main method responsible for compiling and executing migrations.
	 */
	public void migrate() {
		
		//Compilation
		File[] migrations = this.getAllMigrations();
		boolean compilationSuccess = this.compileAllMigrations(migrations);
		if (!compilationSuccess) {
			System.out.println("Migration Operation Suspended!");
			return;
		}
		System.out.println("Migrations compiled successfully!");
		
		
		//Initialise class loader
		File[] migrationClassesFiles = this.getAllMigrationClasses();
		URL migrationsURL;
		try {
			migrationsURL = new URL("file://" + Commands.MIGRATIONS_DESTINATION + "\\");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		loader = new URLClassLoader(new URL[] {migrationsURL});
		
		
		//Run all migrations
		List<String> migrationClassNames = this.getAllClassNames(migrationClassesFiles);
		migrationClassNames.stream()
						   .sorted(Comparator.comparing(this::getTimestamp))
						   .forEach(migrationClassName -> {
							   try {
								   Class migrationClass = this.loadClass(migrationClassName);
								   migrationClass.getMethod("up").invoke(migrationClass.newInstance());
							   } catch (Exception e) {
							   	   System.out.println(migrationClassName + " was not executed!");
								   e.printStackTrace();
							   }
						   });
	}
	
	
	/**
	 * getAllClassNames():
	 * Returns the names of all migration classes that are to executed.
	 * @param migrationClassesFiles : Class Files in the directory
	 * @return : Class list
	 */
	private List<String> getAllClassNames(File[] migrationClassesFiles) {
		return Arrays.asList(migrationClassesFiles)
					 .stream()
					 .map(file -> file.getName())
					 .map(migrationClassName -> migrationClassName.substring(0, migrationClassName.lastIndexOf('.')))
					 .collect(Collectors.toList());
	}
	
	
	/**
	 * getTimestamp():
	 * Returns the rimestamp associated with each class.
	 * This timestamp will be used to decide the order of
	 * execution of migrations.
	 * @param migrationClassName
	 * @return
	 */
	private Long getTimestamp(String migrationClassName) {
		String timestampRegex = ".*Table(\\d+?)$";
		Pattern timestampPattern = Pattern.compile(timestampRegex);
		Matcher timestampMatcher = timestampPattern.matcher(migrationClassName);
		if (timestampMatcher.find()) return new Long(timestampMatcher.group(1));
		return new Long(0);
	}
	
	
	/**
	 * loadClass():
	 * Loads the migration class into the memory.
	 * @param className
	 * @return
	 */
	private Class loadClass(String className) {
		try {
			return this.loader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * getAllMigrationClasses():
	 * Returns the class files of all the compiled migration java files.
	 * @return
	 */
	private File[] getAllMigrationClasses() {
		File migrationsDirectory = new File(Commands.MIGRATIONS_DESTINATION);
		if (migrationsDirectory.exists() && migrationsDirectory.isDirectory())
			return migrationsDirectory.listFiles((directory, fileName) -> {
				String migrationClassFileRegex = "^(Create|Alter)(([A-Z][a-z]+)+)Table[0-9]+\\.class$";
				return Pattern.compile(migrationClassFileRegex).matcher(fileName).matches();
			});
		return null;
	}
	
	
	/**
	 * compileAllMigrations():
	 * The method that uses JavaCompiler instance to compile all migrations
	 * in runtime.
	 * @param migrations : .java migration files
	 * @return : result of compilation.
	 */
	private boolean compileAllMigrations(File[] migrations) {
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(new DiagnosticCollector<>(), null, null);
		Iterable compilationUnit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(migrations));
		//DIAGNOSTIC LISTENER SUPPRESS NOTES OR WARNINGS AND REPORT ERRORS IF ANY
		DiagnosticListener diagnosticListener = diagnostic -> {
			if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
				System.out.println("Migrations have errors:\n" + diagnostic.getMessage(Locale.getDefault()));
			}
		};
		return javaCompiler.getTask(null, fileManager, diagnosticListener, Arrays.asList(new String[]{"-Xlint:none"}), null, compilationUnit).call();
	}
	
	/**
	 * getAllMigrations():
	 * Returns all the .java files in the migrations directory
	 * for compilation.
	 * @return
	 */
	private File[] getAllMigrations() {
		File migrationsDirectory = new File(Commands.MIGRATIONS_DESTINATION);
		if (migrationsDirectory.exists() && migrationsDirectory.isDirectory())
			return migrationsDirectory.listFiles((directory, fileName) -> {
				String migrationFileRegex = "^(Create|Alter)(([A-Z][a-z]+)+)Table[0-9]+\\.java$";
				return Pattern.compile(migrationFileRegex).matcher(fileName).matches();
			});
		return null;
	}
	
	private URLClassLoader loader;
}
