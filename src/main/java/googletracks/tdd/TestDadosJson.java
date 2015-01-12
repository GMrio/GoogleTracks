package googletracks.tdd;

import com.google.gson.Gson;

import googletracks.entities.DadosJsonVO;
import googletracks.model.DadosJson;

public class TestDadosJson {
	public static void main(String[] args) {
		
		DadosJsonVO dados = new DadosJsonVO();
		
		
		DadosJson dadosJson = new DadosJson();
		
		dadosJson.setBateria("aasdasdasd");
		dadosJson.setNoTelefone("2133123123131");
		
		dados.getCrumbs().add(dadosJson);
		
		Gson g = new Gson();
		
		
		String json = g.toJson(dados);
		System.out.println(json);
	}
	
}
