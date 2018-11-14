package sistemaDeLocacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frameworkUI.CrudFramework;
import sampleApplication.MySqlConnection;
import framework.TableObject;;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		
		MySqlConnection teste = new MySqlConnection();
		
		teste.createConnection();
		
		
		//Dodos de exemplo
		Veiculo veiculo = new Veiculo("RenavamTeste", "MarcaTeste", "ModeloTeste", 100.05);		
		Cliente cliente = new Cliente("CPFExemplo", "NomeExemplo");
		
		List<TableObject> tables = new ArrayList<>();		

		tables.add(veiculo);
		tables.add(cliente);
		
		new CrudFramework(tables, teste);

	}

}