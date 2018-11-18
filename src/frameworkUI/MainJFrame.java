package frameworkUI;
import javax.swing.*;

import framework.SqlConnection;
import framework.TableObject;

import java.awt.event.*;
import java.util.List;
import framework.SqlConnection;

public class MainJFrame extends JFrame {

  /**
   * Cosntrutor que isntancia um JPanel e o adiciona a este JFrame.
   */
  public MainJFrame(List<TableObject> tables, SqlConnection connection) {

    this.setTitle("Sistema de Locações");

    this.setSize(600, 200);
    
    //Adiciona a capacidade de fechar a janela
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    
    
    
    //Instancia um novo JPanel
    MainJPanel panel = new MainJPanel(tables, connection); 
    
    
    
    
    //adicona o JPanel a este JFrame
    this.getContentPane().add( panel ); 
    
    //manda mostrar o JFrame
    this.show(); 
  }
}
