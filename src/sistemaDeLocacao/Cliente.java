package sistemaDeLocacao;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Cliente extends TableObject {

	String cpf;
	String nome;
	int locacao_id;
	
	public Cliente(String cpf, String nome, int locacao_id) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		
		primaryKey.add("id");
		relations.add(new Locacao(id, 0, "", "", 0.0));
	}

	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		cpf = dict.get("cpf").toString();
		nome = dict.get("nome").toString();
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("cpf", cpf);
		obj.put("nome", nome);
		
		return obj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getLocacao_id() {
		return locacao_id;
	}

	public void setLocacao_id(int locacao_id) {
		this.locacao_id = locacao_id;
	}
}
