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
		Veiculo veiculo = new Veiculo("RenavamTeste", "MarcaTeste", "ModeloTeste", 100.05, 0);		
		Cliente cliente = new Cliente("CPFExemplo", "NomeExemplo", 0);
		Locacao locacao = new Locacao(0, 0, "", "", 0.0);
		
		List<TableObject> tables = new ArrayList<>();		

		tables.add(veiculo);
		tables.add(cliente);
		tables.add(locacao);
		
		new CrudFramework(tables, teste);

	}

}