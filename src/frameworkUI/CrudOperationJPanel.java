package frameworkUI;

import framework.TableObject;
import framework.SqlConnection;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

//Esta classe cria uma janela para realizar as operações CRUD de cada Classe.
public class CrudOperationJPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	Map<String, JTextField> textFields; //Caixas de texto dentro da janela.
	JButton mainButton; //Botão principal da janela que conterá o nome da operação selecionada.
	JButton secondaryButton; //Botão secundário, apenas para operação de Update.
	CrudOperation operation;
	SqlConnection connection;
	TableObject object;

	public CrudOperationJPanel(TableObject object, CrudOperation operation, SqlConnection connection) {
		this.textFields = new HashMap<>();
		this.mainButton = new JButton(operation.getName());
		this.operation = operation;
		this.connection = connection;
		this.object = object;

		Map<String, Object> dicObject = object.convertToDict();
		
		if(operation == CrudOperation.ListAll) {
			
			ArrayList<String> columnNames = new ArrayList<String>();
			ArrayList<String> row = new ArrayList<String>();
			Object[][] rowData;
			
			for (Map.Entry<String, Object> entry : dicObject.entrySet()) {
				String key = entry.getKey();
				JTable table; //Tabela
				
				JLabel label = new JLabel(key.substring(0, 1).toUpperCase() + key.substring(1)); //Nome da Coluna
				label.setToolTipText(dicObject.get(key).getClass().getSimpleName()); //Label recebe Simple Name do dicionário.
				
				//Se a coluna é de uma Primary Key, adiciona a informação à coluna.
				if(object.getPrimaryKey().contains(key)) {
					label.setToolTipText(label.getToolTipText() + "(PK)");
				}

				//Se a coluna é de uma Foreign Key, adiciona esta mensagem a Tooltip à esquerda da caixa de texto.
				if(object.getForeignKey().keySet().contains(key)) {
					label.setToolTipText(label.getToolTipText() + "(FK: " + object.getForeignKey().keySet() + ")");
				}
				
				columnNames.add(label.toString());
				row.add(key.toString());
				//rowData.add(row);
				
				//Agora adiciona a caixa e a label dentro do painel.
			}
			
			//table = new JTable(columnNames, rowData);
		}
		
		else {
		
			//Esta variável controla a posição dos objetos no painel.
			int yPosition = 10;
	
			//Esta função pega informaçoes, constrói caixas de texto e labels e as insere no painel da operação.
			for (Map.Entry<String, Object> entry : dicObject.entrySet()) {
				String key = entry.getKey();
				JTextField textField = new JTextField(); //Caixa de texto
				JLabel label = new JLabel(key.substring(0, 1).toUpperCase() + key.substring(1)); //Label da caixa de texto
	
				label.setToolTipText(dicObject.get(key).getClass().getSimpleName()); //Label da Caixa de Texto recebe Simple Name do dicionário.
				
				//Se o botão é de uma Primary Key, adiciona esta mensagem a Tooltip à esquerda da caixa de texto. 
				if(object.getPrimaryKey().contains(key)) {
					label.setToolTipText(label.getToolTipText() + " - Primary Key ");
				}
	
				//Se o botão é de uma Foreign Key, adiciona esta mensagem a Tooltip à esquerda da caixa de texto.
				if(object.getForeignKey().keySet().contains(key)) {
					label.setToolTipText(label.getToolTipText() + "- Foreign Key: Class:" + object.getForeignKey().keySet()	+ " Column: " + object.getForeignKey().values());
				}
	
				//Posições da Caixa de Texto e da Label na janela de operação. 
				textField.setBounds(250, yPosition, 100, 30);
				label.setBounds(100, yPosition, 120, 30);
				
				//Obteve as informações para construir uma caixa de texto e um label para esta caixa. Já pode adicionar a caixa e a label dentro do painel.
				this.textFields.put(key, textField);
				this.add(textField);
				this.add(label);
				 
				if ((operation != CrudOperation.Create && operation != CrudOperation.Update) && key != "id") {
					textField.hide();
				}
				
				yPosition = yPosition + 50;
			}
		
			//Adicionadas caixas de texto e labels da classe, o botão referente à operação é adicionado à janela.
			mainButton.setBounds(250, yPosition, 100, 30);
			this.add(mainButton);
			this.mainButton.addActionListener(this);//Adiciona um listener para o botão - ao clicar no botão executará tal função (especificada abaixo).   
	
			//Se a operação é UPDATE, adiciona à janela um segundo botão chamado GET.
			if(operation == CrudOperation.Update) {
				secondaryButton = new JButton("Get");
				secondaryButton.setBounds(100, yPosition, 80, 30);
				this.add(secondaryButton);
				this.secondaryButton.addActionListener(this);
				this.mainButton.setEnabled(false);
			}
		}
	}

	//Metodo que trata quando um botão é acionado.
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.mainButton) {
			switch (this.operation) {
			case Create:
				this.create();
				break;
			case Read:
				this.read();
				break;
			case ListAll:
				this.listAll();
				break;
			case Update:
				this.update();
				break;
			case Delete:
				this.delete();
				break;
			}
		} else if (e.getSource() == this.secondaryButton) { //Ao pressionar o botão secundário na janela de UPDATE, ele executa a função READ e em seguida...
			if (this.read()) {
				this.mainButton.setEnabled(true); //Botão principal é habilitado.
				this.secondaryButton.setEnabled(false); //Botão secundário é desabilitado.

				for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
					if(entry.getKey() == "id"){
						entry.getValue().setEnabled(false);
					}
				}
			}
		}
	}

	
	//A seguir estão os códigos a serem executados ao se clicar no botão de operação da janela.
	
	public void create() {
		HashMap<String, Object> properties = new HashMap<>();	
		
		//Cria um mapa com as informações que o usuário escreveu na janela.
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();

			//Caso o usuário não preencha o campo ID, o banco de dados o auto incrementa.
			if (key == "id") {
				if (value.equals(""))
					value = 0;
			}

			// Adiciona aspas simples nas STRINGS (VARCHAR), pois o banco nao aceita sem.
			if(object.convertToDict().get(key).getClass().getSimpleName().equals("String"))
				value = "'" + value + "'";
			
			
			properties.put(key, value);
		}

		try {
			object.setProperties(properties);
			
		} catch (NumberFormatException e) {
			System.out.println(e);
			showError("Algum dos campos não foi preenchido corretamente! ");
			return;
		}

		try {
			connection.createObject(object);
			showMessage(object.getClass().getSimpleName() + " criado com sucesso");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		showError("Nao foi possível criar o objeto " + object.getClass().getSimpleName() + "\n" + e.getMessage());
			return;

		}

		CrudOperationJFrame relation = null;

		for(TableObject r: object.getRelations()){
			r.setId(object.getId());

			relation = new CrudOperationJFrame(CrudOperation.Create, r, connection);
		}
	}

	public boolean read() {
		try {
			String id = "";
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
				String key = entry.getKey();
				if (key == "id") {
					JTextField idTextField = entry.getValue();
					id = idTextField.getText();
					object.setId(Integer.parseInt(id));
					break;
				}
			}

			TableObject readObject = connection.readObject(object, Integer.parseInt(id));
			
			Map<String, Object> objectProperties = readObject.convertToDict();
			
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
				String objectProperty = objectProperties.get(entry.getKey()).toString();
				JTextField textField = entry.getValue();
				textField.setText(objectProperty);
				textField.show();
			}
			
		} catch (NullPointerException e) {
			showMessage("Objeto nao encontrado");
			return false;
		
		} catch (Exception e) {
			showMessage(e.toString());
			return false;
		}
		
		return true;
	}
	
	public boolean listAll() {
		try {
			String id = "";
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
				String key = entry.getKey();
				if (key == "id") {
					JTextField idTextField = entry.getValue();
					id = idTextField.getText();
					object.setId(Integer.parseInt(id));
					break;
				}
			}

			TableObject readObject = connection.readObject(object, Integer.parseInt(id));
			
			Map<String, Object> objectProperties = readObject.convertToDict();
			
			for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
				String objectProperty = objectProperties.get(entry.getKey()).toString();
				JTextField textField = entry.getValue();
				textField.setText(objectProperty);
				textField.show();
			}
			
		} catch (NullPointerException e) {
			showMessage("Objeto nao encontrado");
			return false;
		
		} catch (Exception e) {
			showMessage(e.toString());
			return false;
		}
		return true;

	}

	public void update() {
		String id = "";
		HashMap<String, Object> properties = new HashMap<>();
		properties = new HashMap<>();
		
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue().getText();

			if (key == "id")
				id = value.toString();		

			// Adiciona aspas simples nas STRINGS (VARCHAR), pois o banco nao aceita sem.
			if(object.convertToDict().get(key).getClass().getSimpleName().equals("String"))
				value = "'" + value + "'";

			properties.put(key, value);
		}
		
		object.setProperties(properties);
		
		try {
			connection.updateObject(object, Integer.parseInt(id));
			showMessage(object.getClass().getSimpleName() + " alterado com sucesso");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showError("Nao foi possível alterar o objeto " + object.getClass().getSimpleName() + "\n" + e.getMessage());
		}
	}

	public void delete() {
		String id = "";
		
		for (Map.Entry<String, JTextField> entry : this.textFields.entrySet()) {
			String key = entry.getKey();
			
			if (key == "id") {
				JTextField idTextField = entry.getValue();
				id = idTextField.getText();
				object.setId(Integer.parseInt(id));
				
				try {
					connection.deleteObject(object);
					showMessage(object.getClass().getSimpleName() + " removido com sucesso");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Nao foi possível remover o objeto " + object.getClass().getSimpleName() + "\n" + e.getMessage());
				}
			}
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERRO", JOptionPane.ERROR_MESSAGE);
	}
}