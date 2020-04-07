package testing;

import app.DatabaseMigrator;
import app.database.ColumnBuilder;

public class TestDatabaseMigrator {
	public static void main(String[] args) {
		DatabaseMigrator.create("users", (table) -> {
			/*table.setId(1);
			table.setName("Dhiresh");
			table.setEmail("dhireshk.hirani@gmail.com");
			table.setPhone("9867371111");*/
			
			table.id();
			table.addColumn(ColumnBuilder.string("title"));
			table.addColumn(ColumnBuilder.string("slug").nullable().unique());
			table.addColumn(ColumnBuilder.text("body"));
		});
	}
}
