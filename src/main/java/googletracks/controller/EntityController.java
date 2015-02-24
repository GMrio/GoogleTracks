package googletracks.controller;

import java.util.Date;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.DatabaseFileDAO;
import googletracks.dao.GoogleDAO;
import googletracks.dao.LogDAO;
import googletracks.entities.DadosJsonVO;
import googletracks.entities.DatabaseFileManager;
import googletracks.model.DadosJson;
import googletracks.model.DatabaseFile;
import googletracks.regras.NoTelefoneRegras;

public class EntityController {

	LogDAO logDAO = new LogDAO();

	/**************************************************
	 * CRIAR ENTITY ATRAVES DO ARQUIVO DADOS.JSON
	 **************************************************/

	public void createEntityByDadosJson() {

		try {
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
			GoogleDAO googleDAO = new GoogleDAO();

			DadosJsonVO documentsJonsVO = new DadosJsonVO();
			// Coletando todos os dados do arquivo JSON
			documentsJonsVO = dadosJsonDAO.findAllDadosJson();


			for (DadosJson doc : documentsJonsVO.getCrumbs()) {
				
				// Procura por um ID na database
				String result = databaseDAO.findByIdGoogleFromNoTelefone(doc.getNoTelefone());

				/**
				 * Cadastrando na GOOGLE
				 */

				if (result == null) {
					logDAO.createINFO("Inserindo o telefone na Google : " + doc.getNoTelefone().toString());
					// Cadastra na Google e pega o Id.
					String idGoogle = googleDAO.createEntity(doc.getNoTelefone().toString());
					
					logDAO.createINFO("Id gerada da Google : " + idGoogle);

					
					
					if (idGoogle == null || idGoogle.trim().equalsIgnoreCase("null")) {
						logDAO.createERROR("ERRO NA TRASAÇÃO DO TELEFONE : " + doc.getNoTelefone());
						logDAO.createERROR("DENTRO DO IF.. result = null, EntityController.createEntityByDadosJson()" );
						System.out.println("erro na transação, tente novamente ou consulte o suporte !");
						dadosJsonDAO.saveDadosJsonNotSend(doc);
					}

					/**
					 * Cadastrando na DataBase
					 */
					DatabaseFile databaseFile = new DatabaseFile(idGoogle, doc.getNoTelefone());
					databaseDAO.saveEntities(databaseFile);
					
					System.out.println("idGoogle=" + idGoogle + " , noTelefone=" + doc.getNoTelefone());
					
				} else {
					System.out.println("NoTelefone já existente :" + doc.getNoTelefone());
					System.out.println("Seu ID:" + result);
				}
			}
			System.out.println("FIM");
		} catch (Exception e) {
			String erro = e.getMessage();
			logDAO.createERROR("ERRO no EntityController.createEntityByDadosJson()");
			logDAO.createERROR(erro);
			e.printStackTrace();
		}
	}
	
	
	/**************************************************
	 * CRIAR ENTITY ATRAVES DO ARQUIVO DADOS JSON NÃO ENVIADO
	 **************************************************/

	public void createEntityDadosNaoEnviados(){
		
		DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
		DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
		GoogleDAO googleDAO = new GoogleDAO();
		LogDAO logDAO = new LogDAO();
		
		DadosJsonVO documentsJonsVO = new DadosJsonVO();
		
		try {
			
			int count = 3;
			while(count != 0){
				
				documentsJonsVO = dadosJsonDAO.findAllDadosJsonNotSend();
				
				if(documentsJonsVO.getCrumbs().size() == 1){ // Igual a 1 pois já existe um dado nulo no arquivo
					break;
				}
				
				//Repetindo os passos de RECADASTRO DO GOOGLE
				for(DadosJson doc : documentsJonsVO.getCrumbs()){
					
					String result = databaseDAO.findByIdGoogleFromNoTelefone(doc.getNoTelefone());
					
					/**
					 * Recadastrando na GOOGLE
					 */
					
					if(result == null){	
						
						logDAO.createINFO("Inserindo o telefone na Google : " + doc.getNoTelefone());
						String idGoogle = googleDAO.createEntity(doc.getNoTelefone());
						
						if(idGoogle == null || idGoogle.trim().equalsIgnoreCase("null")){
							logDAO.createERROR("ERRO NA TRASAÇÃO DO TELEFONE : " + doc.getNoTelefone());							
							count = count - 1;
						}
						
						DatabaseFile databaseFile = new DatabaseFile(idGoogle, doc.getNoTelefone());
						boolean check = databaseDAO.saveEntities(databaseFile);
						
						if(check){
							dadosJsonDAO.removeDadosJsonNotCreate(doc);
							logDAO.createINFO("O NUMERO FOI CADASTRADO NA GOOGLE : " + doc.getNoTelefone());
						} else {
							count = count - 1;
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
	 * CADASTRANDO ENTITY POR INSERÇÃO
	 **************************************************/
	public void createByTelefone(String noTelefone){
		
			NoTelefoneRegras regras = new NoTelefoneRegras();
			
			if(regras.noTelefoneCorreto(noTelefone)){
			
				try {
					GoogleDAO googleDAO = new GoogleDAO();
					DatabaseFileDAO databaseDAO = new DatabaseFileDAO();
					
					
					String result = databaseDAO.findByIdGoogleFromNoTelefone(noTelefone);  // verifica se existe o telefone na base
					
					
					if(result == null){
						String idGoogle = googleDAO.createEntity(noTelefone); // cria um entityId
						if(idGoogle == null || idGoogle.equalsIgnoreCase("null")){
							//System.out.println("Ocorreu um erro na comunicacao com a Google tente mais tarde");
							logDAO.createERROR(noTelefone);
							logDAO.createERROR("Ocorreu um erro na comunicacao com a Google tente mais tarde");
						} else {
							DatabaseFile databaseFile = new DatabaseFile(idGoogle, noTelefone); 
							databaseDAO.saveEntities(databaseFile); //salva na base de dados
							System.out.println("Cadastrado com Sucesso ! ");
							System.out.println("noTelefone: " + noTelefone + ", idGoogle:" + idGoogle);
							logDAO.createINFO("noTelefone: " + noTelefone + ", idGoogle:" + idGoogle);
						}
						
					} else {
						
						System.out.println("O numero=" + noTelefone + "Ja existe e seu entityID=" + result);
					}
				} catch (Exception e) {
					logDAO.createERROR("Ocorreu um erro EntityController createEntity");
					logDAO.createERROR(e.getMessage());
				}
				
			}
			
		
	}
	
	
	/**************************************************
	 * TRAZER TODA ENTIDADE DO DATABASE FILE
	 **************************************************/
	public void findAllEntity(){
		DatabaseFileManager database = new DatabaseFileManager();
		DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
		try {
			
			database = databaseFileDAO.findAllDatabaseFile();
			for (DatabaseFile data : database.getEntities()) {
				System.out.println("idGoogle:" + data.getId() + " ,entity:" + data.getName());
			}
			logDAO.createINFO(database.getEntities().toString());
			logDAO.createINFO("Trazendo toda a database file");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/**************************************************
	 * TRAZENDO ID GOOGLE PELO TELEFONE INFORMADO
	 **************************************************/
	
	public String findIdGoogleByNoTelefone(String noTelefone){
		
		NoTelefoneRegras regras = new NoTelefoneRegras();
		
		if(regras.noTelefoneCorreto(noTelefone)){
			DatabaseFileDAO dao = new DatabaseFileDAO();
			
			try {
				String idGoogle = dao.findByIdGoogleFromNoTelefone(noTelefone);
				if(idGoogle.equals("") || idGoogle == null){
					System.out.println("Não existe esse número cadastrado");
					return null;
				}else {
					return idGoogle;
				}
			} catch (Exception e) {
				logDAO.createERROR("Error EntityController.findIdGoogleByNoTelefone");
				logDAO.createERROR(e.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}
	
	
	
	/**************************************************
	 * REMOVENDO ENTITY
	 **************************************************/
	public void removeEntity(String noTelefone){
		try {
			
			boolean check = false;
			
			GoogleController googleController = new GoogleController();
			String idGoogle = findIdGoogleByNoTelefone(noTelefone);
			
			/*****
			 * 
			 * Check EntityID
			 * 
			 *****/
			if(idGoogle != null && !idGoogle.trim().equals("")){ // verifica o noTelefone do dados.json
				/******
				 * 
				 * Verifica se existe Crumbs da EntityId
				 * 
				 *****/
				CrumbsController crumbsController = new CrumbsController();
				
				long timestamp = System.currentTimeMillis() / 1000L;
				int count = crumbsController.verificaSeContemCrumbsHistory(idGoogle, timestamp);
				//System.out.println(count);
				
				
				if(count <= 0){
					check = googleController.removeIdGoogle(idGoogle); //remove o entityId da google
					DatabaseFile databaseFile = new DatabaseFile(idGoogle, noTelefone);
					
					
					
					
					/******
					 * 
					 * Deleta Na Google
					 * 
					 */
					
					if(check){ //verifica se a remoção do entityId na Google foi ok
						DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO(); 
						check = databaseFileDAO.removeDatabaseFile(databaseFile); //remove o entityId do entity.txt
						
						
						
						/****
						 * 
						 * Deleta no arquivo (entitys.txt)
						 * 
						 */
						
						if(!check){
							//System.out.println("Ocorreu um erro ao Excluir, tente novamente");
						} else {
							System.out.println("Numero excluido com sucesso !");
						}
					
					} //Deleta na Google  
					
				
				} else if (count > 0) { // verifica se tem Crumbs
					System.out.println("Existe Crumbs no noTelefone, não pode excluir a entityId");
				}
				
			} // fechando o if do check Id no entity.txt
			
			
			
			else {
				System.out.println("Numero não existe");
			} // fechando o else do check Id no entity.txt
			
			
		} catch (Exception e) {
			logDAO.createERROR("Erro no EntityController.removeEntity");
			logDAO.createERROR(e.getMessage());
		}
		
	} 
	
	
	
	
	/**************************************************************
	 *
	 * 
	 * CHECK SE O NO EXISTE
	 * 
	 *************************************************************/
	public boolean existeNoTelefone(String noTelefone){
		try {
			
			
			GoogleController googleController = new GoogleController();
			String idGoogle = findIdGoogleByNoTelefone(noTelefone);
			
			logDAO.createINFO("VERIFICANDO A EXISTENCIA DO noTelefone : " + noTelefone);
			if(idGoogle != null && !idGoogle.trim().equals("")){ 
				logDAO.createINFO("O noTelefone : " + noTelefone + " EXISTE !");
				return true;
				
			} else {
				logDAO.createINFO("O noTelefone : " + noTelefone + " NÃO EXISTE !");
				return false;
			}
			
			
		} catch (Exception e) {
			logDAO.createERROR("Erro no EntityController.existeNoTelefone() ");
			logDAO.createERROR(e.getLocalizedMessage());
			logDAO.createERROR(e.getMessage());
			return false;
			
		}
	}
	
}
