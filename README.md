# Java Database Migrator

A tool that can be used to migrate an entire database from one system to another by the use of custom migration files.
A .env file is required for migration which specifies the path to migrations folder as well as all database variables.
It provides an interpreter with fixed commands for creation of migration files and for migration.

### Commands List:

- cm - Creates a Migration file
 
 ```
  Syntax:
  cm migration-name [model-name]
 ``` 
  
- migrate - Runs the migrations.

- help - Will display the list of the available commands for the interpreter.
