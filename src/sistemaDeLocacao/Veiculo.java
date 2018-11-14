package sistemaDeLocacao;

import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Veiculo extends TableObject {

	private String renavam;
	private String marca;
	private String modelo;
	private double valorDiaria;
	
	public Veiculo(String renavam, String marca, String modelo, double valorDiaria) {
		super();
		this.renavam = renavam;
		this.marca = marca;
		this.modelo = modelo;
		this.valorDiaria = valorDiaria;
	}

	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		renavam = dict.get("renavam").toString();
		marca = dict.get("marca").toString();
		modelo = dict.get("modelo").toString();
		valorDiaria = (Double.parseDouble(dict.get("valorDiaria").toString()));
	}
	
	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("renavam", renavam);
		obj.put("marca", marca);
		obj.put("modelo", modelo);
		obj.put("valorDiaria", valorDiaria);
		return obj;
	}
	
	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValor_diaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
}
