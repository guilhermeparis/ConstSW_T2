package framework;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

	public Connection createConnection();
	public boolean createTable();
	public boolean createObject(TableObject obj) throws SQLException;
	public TableObject readObject(TableObject table, int id) throws SQLException;
	public TableObject listAllObject(TableObject table, int id) throws SQLException;
	public boolean updateObject(TableObject obj, int id) throws SQLException;
	public boolean deleteObject(TableObject obj) throws SQLException;	
}
