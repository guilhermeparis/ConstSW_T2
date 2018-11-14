package frameworkUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import framework.SqlConnection;
import framework.TableObject;

import javax.swing.JComboBox;
import framework.SqlConnection;;

public class MainJPanel  extends JPanel implements ActionListener {
  /**
   * Componente de label
   */
  private JComboBox<String> combobox;
  private JButton createButton;
  private JButton readButton;
  private JButton updateButton;
  private JButton deleteButton;
  private List<TableObject> tables;
  private SqlConnection connection;
  
  public MainJPanel(List<TableObject> tables, SqlConnection connection)  {
    //Instancia um novo label e um novo botão
    createButton = new JButton("Create");
    readButton = new JButton("Read");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    combobox = new JComboBox<>();
    this.tables = tables;
    this.connection = connection;
       
    
    //seta os limites dos componentes
    
    for (TableObject table : this.tables) {
    		combobox.addItem(table.getClass().getSimpleName());
    }
    
    //Adiciona componentes a este Panel
    this.add(this.combobox);
    this.add(this.createButton);
    this.add(this.readButton);
    this.add(this.updateButton);
    this.add(this.deleteButton);
    
    //adiciona ao um botão um "escutador", responsável por tratar seus cliques.
    this.createButton.addActionListener(this);
    this.readButton.addActionListener(this);
    this.updateButton.addActionListener(this);
    this.deleteButton.addActionListener(this);
  }

  /**
   * Método que trata quando uma ação é executada 
   */
  public void actionPerformed(ActionEvent e)  {
	  //Pega o objeto selecionado no combobox
	  String selectedTableName = (String) combobox.getSelectedItem();
	  TableObject selectedObject = null;
	  
	  for (TableObject table : this.tables) {
  		if(selectedTableName.equals(table.getClass().getSimpleName())) {
  			selectedObject = table;
  			break;
  		}
	  }
	  
	  
    //Verifica qual botão foi acionado
    if (e.getSource()==this.createButton) {
    		new CrudOperationJFrame(CrudOperation.Create, selectedObject, connection);
    } else if (e.getSource()==this.readButton) {
    		new CrudOperationJFrame(CrudOperation.Read, selectedObject, connection);
    } else if (e.getSource()==this.updateButton) {
    		new CrudOperationJFrame(CrudOperation.Update, selectedObject, connection);
    } else if (e.getSource()==this.deleteButton){
    		new CrudOperationJFrame(CrudOperation.Delete, selectedObject, connection);
    }  
       
    
  }

}
