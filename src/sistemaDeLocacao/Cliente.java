package sistemaDeLocacao;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Cliente extends TableObject {

	private String cpf;
	private String nome;
	
	public Cliente(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
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
	
}
