package sampleApplication;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Concessionaria extends TableObject {

	String name;
	String endereco;
	String descricao;
	int capacidadeVeiculos;
	String dataCriacao;
	String tipo;
	Boolean oficina;
	Boolean importado;

	public Concessionaria(String name, String endereco, String descricao, int capacidadeVeiculos, String dataCriacao,
			String tipo, Boolean oficina, Boolean importado) {
		super();
		this.name = name;
		this.endereco = endereco;
		this.descricao = descricao;
		this.capacidadeVeiculos = capacidadeVeiculos;
		this.dataCriacao = dataCriacao;
		this.tipo = tipo;
		this.oficina = oficina;
		this.importado = importado;
		
	}


	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		name = dict.get("name").toString();
		endereco = dict.get("endereco").toString();
		descricao = dict.get("descricao").toString();
		capacidadeVeiculos = (Integer.parseInt(dict.get("capacidadeVeiculos").toString()));
		dataCriacao = dict.get("dataCriacao").toString();
		tipo = dict.get("tipo").toString();
		oficina = Boolean.valueOf(dict.get("oficina").toString());
		importado = Boolean.valueOf(dict.get("importado").toString());
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("name", name);
		obj.put("endereco", endereco);
		obj.put("descricao", descricao);
		obj.put("capacidadeVeiculos", capacidadeVeiculos);
		obj.put("dataCriacao", dataCriacao);
		obj.put("tipo", tipo);
		obj.put("oficina", oficina);
		obj.put("importado", importado);

		return obj;
	}

}
