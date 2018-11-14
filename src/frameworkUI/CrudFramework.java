package frameworkUI;

import framework.TableObject;
import framework.SqlConnection;

import java.net.ConnectException;
import java.util.List;

public class CrudFramework {

	static List<TableObject> tables;
	static SqlConnection connection;
	
	public CrudFramework(List<TableObject> tables, SqlConnection connection) {
		CrudFramework.tables = tables;
		CrudFramework.connection = connection;
		main(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainJFrame(tables, connection);
	}

}
