package googletracks.tdd;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.GoogleDAO;
import googletracks.entities.EntityRetrieveIds;

import com.google.gson.Gson;

public class LeituraDoArquivoDadosJSON {
	public static void main(String[] args) {
		
		System.getProperties().put("proxySet", "true");
	    System.getProperties().put("proxyHost", "10.2.118.64"); //proxy.rio.rj.gov.br  10.2.118.64
	    System.getProperties().put("proxyPort", "8080");
		
		
		DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
		Gson gson = new Gson();
		
		//System.out.println(dadosJsonDAO.lerDadosJson());
		
		System.out.println(gson.toJson(dadosJsonDAO.lerDadosJson()));
		
		GoogleDAO googleDAO = new GoogleDAO();
		EntityRetrieveIds ids = googleDAO.criarEntityIdPorLista(dadosJsonDAO.lerDadosJson());
		
		System.out.println(ids);
		
	}

}


