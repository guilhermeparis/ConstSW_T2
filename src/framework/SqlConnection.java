package framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import framework.DatabaseConnection;
import framework.TableObject;

public abstract class SqlConnection implements DatabaseConnection {

	protected Connection conn = null;

	@Override
	public boolean createTable() {
		return false;
	}

	@Override
	public boolean createObject(TableObject obj) throws SQLException {

		String sql = "INSERT INTO " + obj.getClass().getSimpleName() + " (" + obj.convertToDict().keySet().toString().replace("[", "").replace("]", "") + ")"
				+" VALUES ("+ obj.convertToDict().values().toString().replace("[", "").replace("]", "") + ");";

		Statement stmt;
		System.out.println(sql);
		stmt = conn.createStatement();
		stmt.execute(sql);
		
		return true;
	}

	@Override
	public TableObject readObject(TableObject table, int id) throws SQLException {

		String sql = "SELECT * FROM " + table.getClass().getSimpleName() + " WHERE ID = " + id + ";";

		Statement stmt;
		ResultSetMetaData rsmd;
		ResultSet result;

		stmt = conn.createStatement();
		result = stmt.executeQuery(sql);

		rsmd = result.getMetaData();

		table.setId(id);
		Map<String, Object> dict = new HashMap<String, Object>();

		while (result.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
				dict.put(rsmd.getColumnName(i), result.getObject(i));
			}
		}

		table.setProperties(dict);

		return table;

	}

	@Override
	public TableObject listAllObject(TableObject table, int id) throws SQLException {
		String sql = "SELECT * FROM " + table.getClass().getSimpleName() + " WHERE ID = " + id + ";";

		Statement stmt;
		ResultSetMetaData rsmd;
		ResultSet result;

		stmt = conn.createStatement();
		result = stmt.executeQuery(sql);

		rsmd = result.getMetaData();

		table.setId(id);
		Map<String, Object> dict = new HashMap<String, Object>();

		while (result.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
				dict.put(rsmd.getColumnName(i), result.getObject(i));
			}
		}

		table.setProperties(dict);

		return table;
	}

	@Override
	public boolean updateObject(TableObject obj, int id) throws SQLException {

		String sql = "UPDATE " + obj.getClass().getSimpleName()
				+ " SET " + obj.convertToDict().toString().replace("{", "").replace("}", "")
				+ " WHERE ID = " + id + ";";

		Statement stmt;
		System.out.println(sql);
		stmt = conn.createStatement();
		stmt.execute(sql);
		
		return true;
	}

	@Override
	public boolean deleteObject(TableObject obj) throws SQLException {

		String sql = "DELETE FROM " + obj.getClass().getSimpleName() + " WHERE ID = " + obj.getId() + ";";

		System.out.println(sql);
		Statement stmt;
		stmt = conn.createStatement();
		stmt.execute(sql);
		
		return true;
	}

	public boolean createIntermediaryTable(TableObject obj, TableObject obj2) {
		
		//Verifica se a tabela já existe		
		String sql1 = "SELECT * FROM Intermediary" + obj.getClass().getSimpleName() + obj2.getClass().getSimpleName();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sql1);
			return true;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Criar tabela intermediaria entre " + obj.getClass() + " e " + obj2.getClass());
		}		
		
		String sql = "CREATE TABLE Intermediary" + obj.getClass().getSimpleName() + obj2.getClass().getSimpleName()
				+ " ( " + obj.getClass().getSimpleName() + "ID INTEGER, " + obj2.getClass().getSimpleName()
				+ "ID INTEGER," + " FOREIGN KEY FK_" + obj.getClass().getSimpleName() + "("
				+ obj.getClass().getSimpleName() + "ID) REFERENCES " + obj.getClass().getSimpleName() + "(id),"
				+ " FOREIGN KEY FK_" + obj2.getClass().getSimpleName() + "(" + obj2.getClass().getSimpleName()
				+ "ID) REFERENCES " + obj2.getClass().getSimpleName() + "(id));";
				

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean insertRelation(TableObject obj, TableObject obj2) {

		String sql = "INSERT INTO Intermediary" + obj.getClass().getSimpleName() + obj2.getClass().getSimpleName()
				+ " ( " + obj.getClass().getSimpleName() + "ID, " + obj2.getClass().getSimpleName() + "ID) values ("
				+ obj.getId() + ", " + obj2.getId() + ");";

		Statement stmt;

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
