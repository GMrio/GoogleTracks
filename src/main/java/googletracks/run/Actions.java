package googletracks.run;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.DatabaseFileDAO;
import googletracks.dao.GoogleDAO;
import googletracks.dao.LogDAO;
import googletracks.entities.DatabaseFileManager;
import googletracks.entities.DadosJsonVO;
import googletracks.entities.EntitysDelete;
import googletracks.entities.EntitysRetrieve;
import googletracks.model.DatabaseFile;
import googletracks.model.DadosJson;
import googletracks.model.Entity2;
import googletracks.services.TracksServices;
import googletracks.utils.RequiredData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class Actions {

	LogDAO log = new LogDAO();
	
	
	/**************************************************
	 * CRIAR ENTITY ATRAVES DO ARQUIVO DADOS.JSON
	 **************************************************/
	
	public void createEntityByDadosJson(){

		try {
			
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
			GoogleDAO googleDAO = new GoogleDAO();
			LogDAO logDAO = new LogDAO();
			
			
			/*****************************************************************************
			 * 				CRIAÇÃO AUTOMATICA DE ARQUIVOS
			 ****************************************************************************/
			databaseDAO.createFileEntities();
			dadosJsonDAO.createFileDadosJsonNotSend();
			
			
			DadosJsonVO documentsJonsVO = new DadosJsonVO();
			documentsJonsVO = dadosJsonDAO.findAllDadosJson();
			
			
			//Coletando todos os dados do arquivo JSON
			for(DadosJson doc : documentsJonsVO.getCrumbs()){
				
				//Procura por um ID na database 
				String result = databaseDAO.findByIdGoogleFromNoTelefone(doc.getNoTelefone().toString());
				
				/**
				 * Cadastrando na GOOGLE
				 */
				
				if(result == null){
//					System.out.println("ADICIONA AS ENTITYS :" + doc.getNoTelefone());			
					logDAO.createINFO("Inserindo o telefone na Google : " + doc.getNoTelefone().toString());
					
					//Cadastra na Google e pega o Id.
					String idGoogle = googleDAO.createEntity(doc.getNoTelefone().toString());
					logDAO.createINFO("Id gerada da Google : " + idGoogle);
					
					System.out.println("ID = " + idGoogle + " , NUMERO = " + doc.getNoTelefone());
					if(idGoogle == null || idGoogle.equalsIgnoreCase("null")){
//						System.out.println("OCORREU ERRO NA TRASAÇÃO");
//						System.out.println(doc.getNoTelefone());
						logDAO.createERROR("ERRO NA TRASAÇÃO DO TELEFONE : " + doc.getNoTelefone());
						
						dadosJsonDAO.saveDadosJsonNotSend(doc);
					}
					
					
					/**
					 * Cadastrando na DataBase
					 */
					DatabaseFile databaseFile = new DatabaseFile(idGoogle, doc.getNoTelefone());
					databaseDAO.saveEntities(databaseFile);
					
	
				} else {
					System.out.println("Ja existe não cadastra");
					System.out.println(result);
				}
			}
			System.out.println("FIM");
		} catch (Exception e) {
			String erro = e.getMessage();
			log.createERROR(erro);
		}
	}
	
	
	
	
	/**************************************************
	 * CRIAR ENTITY ATRAVES DO ARQUIVO DADOS.JSON
	 **************************************************/

	public void createEntityDadosNaoEnviados(){
		
		DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
		DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
		GoogleDAO googleDAO = new GoogleDAO();
		LogDAO logDAO = new LogDAO();
		
		DadosJsonVO documentsJonsVO = new DadosJsonVO();
		documentsJonsVO = dadosJsonDAO.findAllDadosJson();
		
		try {
			
			
			int count = 3;
			while(count != 0){
				
				documentsJonsVO = dadosJsonDAO.findAllDadosJsonNotSend();
				
				if(documentsJonsVO.getCrumbs().size() == 1){ // Igual a 1 pois já existe um dado nulo no arquivo
					System.out.println("VEM AKI ?");
					break;
				}
				
				
				//Repetindo os passos de RECADASTRO DO GOOGLE
				for(DadosJson doc : documentsJonsVO.getCrumbs()){
					
					String result = databaseDAO.findByIdGoogleFromNoTelefone(doc.getNoTelefone().toString());
					
					/**
					 * REcadastrando na GOOGLE
					 */
					
					if(result == null){	
						logDAO.createINFO("Inserindo o telefone na Google : " + doc.getNoTelefone().toString());
						String idGoogle = googleDAO.createEntity(doc.getNoTelefone().toString());
						count = count - 1;
						if(idGoogle == null || idGoogle.equalsIgnoreCase("null")){
							logDAO.createERROR("ERRO NA TRASAÇÃO DO TELEFONE : " + doc.getNoTelefone());							
							count = count - 1;
						}
						DatabaseFile databaseFile = new DatabaseFile(idGoogle, doc.getNoTelefone());
						boolean check = databaseDAO.saveEntities(databaseFile);
						if(check){
							dadosJsonDAO.removeDadosJsonNotCreate(doc);
							logDAO.createINFO("O NUMERO FOI CADASTRADO NA GOOGLE : " + doc.getNoTelefone());
						}
					} else {
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**************************************************
	 * Deletar para continuar o teste
	 **************************************************/
	
	public void deleteAll(){
		Gson gson = new Gson();
		TracksServices tacksServices = new TracksServices();
		
		String jsonString = tacksServices.requestString("entities/list", "");
		//System.out.println(jsonString);
		EntitysRetrieve entityRetrive = new EntitysRetrieve();
		List<Entity2> entity2 = new ArrayList<Entity2>();
		entityRetrive.setEntities(entity2);
		entityRetrive = gson.fromJson(jsonString, EntitysRetrieve.class); 
//		System.out.println(entityRetrive);
		////////END //////////////////////
		
		
		////DELETE/////
		EntitysDelete entitysDelete = new EntitysDelete();
		int size = entityRetrive.getEntities().size();
		System.out.println(size);
		for(int i = 0 ; i < size ; i++){
			entitysDelete.getEntityIds().add(entityRetrive.getEntities().get(i).getId() );
		}
		jsonString = tacksServices.requestString("entities/delete", gson.toJson(entitysDelete));
		System.out.println(jsonString);
		if(jsonString.equals("{}")){
			System.out.println("De : " + size + " foram tudo deletado com sucesso !");
		}
	}
	
	/**************************************************
	 * TRAZER TODA ENTIDADE DO DATABASE FILE
	 **************************************************/
	public void findAllEntity(){
		Gson gson = new Gson();
		DatabaseFileManager database = new DatabaseFileManager();
		try {
			BufferedReader output = new BufferedReader(new FileReader(RequiredData.FILE_ENTITIES));
			database = gson.fromJson(output, DatabaseFileManager.class);
			for (DatabaseFile data : database.getEntities()) {
				System.out.println("idGoogle:" + data.getId() + " ,entity:" + data.getName());
			}
			log.createINFO("Trazendo toda a database file");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**************************************************
	 * TRAZENDO TODAS QUE NÃO CONSEGUIRAM SER CRIADA
	 **************************************************/
	public void findAllDontCreateEntity(){
		
		try {
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DadosJsonVO documentsJonsVO = new DadosJsonVO();
			documentsJonsVO = dadosJsonDAO.findAllDadosJsonNotSend();
			for(DadosJson doc : documentsJonsVO.getCrumbs()){
				System.out.println(doc);
			}
			log.createINFO("Trazendo todas as que não conseguiram ser enviada.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**************************************************
	 * TRAZENDO ID GOOGLE PELO TELEFONE INFORMADO
	 **************************************************/
	
	public void findIdGoogleByNoTelefone(String noTelefone){
		DatabaseFileDAO dao = new DatabaseFileDAO();
		
		try {
			String idGoogle = dao.findByIdGoogleFromNoTelefone(noTelefone);
			if(idGoogle.equals("") || idGoogle == null){
				System.out.println("Não existe esse número cadastrado");
			}else {
				System.out.println(idGoogle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**************************************************
	 * CADASTRANDO TELEFONE
	 **************************************************/
	public void createTelefone(String noTelefone){
		try {
			GoogleDAO googleDAO = new GoogleDAO();
			DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
			
			
			String result = databaseDAO.findByIdGoogleFromNoTelefone(noTelefone);
			
			if(result == null){
				
				String idGoogle = googleDAO.createEntity(noTelefone);
				if(idGoogle == null || idGoogle.equalsIgnoreCase("null")){
					System.out.println("Ocorreu um erro na comunicação com a Google tente mais tarde");
				} else {
					DatabaseFile databaseFile = new DatabaseFile(idGoogle, noTelefone);
					databaseDAO.saveEntities(databaseFile);
					System.out.println("Cadastrado com Sucesso ! ");
					System.out.println("O numero cadastrado : " + noTelefone);
					System.out.println("O ID da Google : " + idGoogle);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
