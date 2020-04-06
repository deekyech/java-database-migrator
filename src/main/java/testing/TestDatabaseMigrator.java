package testing;

import app.DatabaseMigrator;

public class TestDatabaseMigrator {
	public static void main(String[] args) {
		DatabaseMigrator.create("users", (table) -> {
			table.setId(1);
			table.setName("Dhiresh");
			table.setEmail("dhireshk.hirani@gmail.com");
			table.setPhone("9867371111");
		});
	}
}
