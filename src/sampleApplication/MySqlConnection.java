package sampleApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import framework.DatabaseConnection;
import framework.SqlConnection;
import framework.TableObject;

public class MySqlConnection extends SqlConnection {

	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://localhost:3306/teste";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	@Override
	public Connection createConnection() {

		try {
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Verifique se o banco de dados esta ativo/configurado corretamente!");
			e.printStackTrace();
			System.exit(0);
		}
		// Retorna a conexao aberta
		return conn;
	}

}
