package frameworkUI;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainJTable extends JFrame {
	JPanel background;
	JTable table;
	JScrollPane scroll;
	
	//Uma matriz de dados popula a tabela. Necessita receber os dados do Banco.
	 Object [][] data = {
		        {"Ana Monteiro", "489898"},
		        {"João da Silva", "8903345"},
		        {"Pedro Cascaes", "8705634"}
		    };
	
	 //Um array de colunas identifica os cabeçalhos do Banco de Dados.
	 String [] columns = {"Nome", "CPF"}; 
	 
//	 public MainJTable() {
//		 super("Tabela");
//	 }
	 
	 public void createWindow() {
		 background = new JPanel();
		 background.setLayout(new GridLayout(1, 1));
		 table = new JTable(data, columns);
		 scroll = new JScrollPane(table);
		 background.add(scroll);
		 
		 getContentPane().add(background);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setSize(600,400);
		 setVisible(true);
	 }
	 
	 public static void main (String[] args) {
		 MainJTable tb = new MainJTable();
		 tb.createWindow();
	 }
	 

	 
	//Construtor que instancia um JTable o adicionada ao JFrame
	public MainJTable() {
		this.setTitle("Sistema de Locações");
		this.setSize(600, 200);
	    //Instancia um novo JTable
	    MainJTable table = new MainJTable(); 
	    //Adicona o JPanel a este JFrame.
	    this.getRootPane().add(table);
	    //Exibe o JFrame.
	    this.setVisible(true);
	    this.show();
	}
}