package sampleApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frameworkUI.CrudFramework;
import framework.TableObject;;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		
		MySqlConnection teste = new MySqlConnection();
		
		teste.createConnection();
		
		
		//Dodos de exemplo
		Concessionaria concessionaria = new Concessionaria("Example", "Example", "Example", 0, "Example", "Example", true, false);		
		Carro carro = new Carro("Example", "Example", 0.0, 0);
		CarroConcessionaria cc = new CarroConcessionaria(0, 0, 0);		
		List<TableObject> tables = new ArrayList<>();		

		tables.add(carro);
		tables.add(concessionaria);
		tables.add(cc);
		
		new CrudFramework(tables, teste);

		
	}

}