package googletracks.controller;

import java.io.File;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.DatabaseFileDAO;
import googletracks.dao.LogDAO;
import googletracks.utils.RequiredData;

public class StartUpController {
	
	public void startup (){		
		DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
		DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
		LogDAO logDAO = new LogDAO();
		
		File file = new File(RequiredData.FILE_ENTITIES);
		
		if(file.exists()){
			System.out.println("Ja foi instalado");
		} else {
		
			try {
				databaseDAO.createFileEntities(); // cria o entities.txt
				dadosJsonDAO.createFileDadosJsonNotSend();	// cria o arquivo dados_json_nao_cadastro.json
				dadosJsonDAO.createFileDadosJsonNotSendForCrumbs();// criar dados_json_nao_cadastro_crumbs.json
				logDAO.createFiles(); // Cria logs
				System.out.println("Iniciado com Sucesso");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
