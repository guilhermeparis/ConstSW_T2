package sistemaDeLocacao;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Locacao extends TableObject {
	
	int cliente_id;
	int veiculo_id;
	String dataInicio;
	String dataFim;
	double valorTotal;
	
	public Locacao(int cliente_id, int veiculo_id, String dataInicio, String dataFim, double valorTotal) {
		super();
		this.cliente_id = cliente_id;
		this.veiculo_id = veiculo_id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valorTotal = valorTotal;
	}

	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		cliente_id = Integer.parseInt(dict.get("cliente_id").toString());
		veiculo_id = Integer.parseInt(dict.get("veiculo_id").toString());
		dataInicio = dict.get("dataInicio").toString();
		dataFim = dict.get("dataFim").toString();
		valorTotal = Double.parseDouble(dict.get("valorTotal").toString());
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("cliente_id", cliente_id);
		obj.put("veiculo_id", veiculo_id);
		obj.put("dataInicio", dataInicio);
		obj.put("dataFim", dataFim);
		obj.put("valorTotal", valorTotal);
		
		return obj;
	}	
}