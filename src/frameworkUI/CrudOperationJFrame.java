package frameworkUI;

import framework.TableObject;
import framework.SqlConnection;
import javax.swing.*;
import java.awt.event.*;



public class CrudOperationJFrame extends JFrame {
	
  public CrudOperationJFrame(CrudOperation operation, TableObject object, SqlConnection connection) {

    this.setTitle(object.getClass().getSimpleName());

    this.setSize(600, 400);
    
    //Adiciona a capacidade de fechar a janela
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
    	  	dispose();
      }
    });

    //Instancia um novo JPanel
    CrudOperationJPanel panel = new CrudOperationJPanel(object, operation, connection);
    panel.setLayout(null);
    
    //adicona o JPanel a este JFrame
    this.getContentPane().add(panel); 
    
    //manda mostrar o JFrame
    this.show(); 
  }
}
