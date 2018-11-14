package sampleApplication;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class CarroConcessionaria extends TableObject {

	int carro_id;
	int concessionaria_id;
	int qtd;
 
	
	public CarroConcessionaria(int carro_id, int concessionaria_id, int qtd)
	{
		this.carro_id = carro_id;
		this.concessionaria_id = concessionaria_id;
		this.qtd = qtd;
		
	}


	@Override
	public void setProperties(Map<String, Object> dict) {
		
		setId(Integer.parseInt(dict.get("id").toString()));
		carro_id = Integer.parseInt(dict.get("carro_id").toString());
		concessionaria_id = Integer.parseInt(dict.get("concessionaria_id").toString());
		qtd = Integer.parseInt(dict.get("qtd").toString());
					
	}


	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("carro_id", carro_id);
		obj.put("concessionaria_id", concessionaria_id);
		obj.put("qtd", qtd);
		
		return obj;


	}
	
	
}
