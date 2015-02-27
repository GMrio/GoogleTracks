package googletracks.dao;

import googletracks.entities.CrumbsDelete;
import googletracks.entities.CrumbsRecording;
import googletracks.entities.CrumbsRetrieveHistory;
import googletracks.entities.EntityCreate;
import googletracks.entities.EntityRetrieveIds;
import googletracks.entities.EntitysDelete;
import googletracks.entities.EntitysRetrieve;
import googletracks.entities.Error;
import googletracks.model.Crumbs;
import googletracks.model.Entity1;
import googletracks.model.EntityId;
import googletracks.model.MinId;
import googletracks.model.TypeEntity;
import googletracks.services.TracksServices;

import com.google.gson.Gson;

/**
 * 
 * @author u6448938 / Marcos Felipe
 * 
 * Objeto que se comunica com a Google para trazer os dados desejados.
 *
 *
 */


public class GoogleDAO extends LogDAO{

	private final static String ENTITY_CREATE = "entities/create";
	private final static String ENTITY_DELETE = "entities/delete";
	private final static String ENTITY_LIST = "entities/list";
	
	private final static String CRUMBS_RECORD = "crumbs/record";
	private final static String CRUMBS_GET_HISTORY = "crumbs/gethistory";
	private final static String CRUMBS_DELETE = "crumbs/delete";
	
	private TracksServices tracks = new TracksServices();
	private LogDAO logDAO = new LogDAO();
	
	public String createEntity(String name){
		//System.out.println("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
		
		try {
			super.createINFO("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
			
			Gson gson = new Gson();
			Entity1 entity1 = new Entity1();
			entity1.setName(name);
			entity1.setType(TypeEntity.PERSON);
			
			EntityCreate create = new EntityCreate();
			create.getEntities().add(entity1);
			
			String requestBody = gson.toJson(create);
			
			
			//System.out.println("DADOS CRIADO PARA JSON");
			
			//System.out.println(requestBody);
			super.createINFO("DADOS CRIADOS PARA JSON");
			super.createINFO(requestBody);
			
			
			String response = tracks.requestString(ENTITY_CREATE, requestBody);
			
			
			EntityRetrieveIds entityRetriveIds = new EntityRetrieveIds();
			
			String idGoogle = "";
			try {
				entityRetriveIds = gson.fromJson(response, EntityRetrieveIds.class);
				idGoogle = entityRetriveIds.getEntityIds().get(0).toString();
			} catch (Exception e) {
				System.out.println(response);
				idGoogle = null;
			}

			return idGoogle;
			
		} catch (Exception e) {
			
			super.createERROR("ERRO NA TRANSACAO COM O GOOGLE , GoogleDAO.createEntity " );
			super.createERROR(e.getMessage());
			super.createERROR(e.getLocalizedMessage());
			super.createERROR(e.getCause().toString());
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
		criando lista de entity ID
	
	*/
	
	public EntityRetrieveIds criarEntityIdPorLista(EntityCreate entityCreate){
		//System.out.println("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
		
		try {
			super.createINFO("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
			
			Gson gson = new Gson();
			
			String requestBody = gson.toJson(entityCreate);
			
			//System.out.println("GSON PRA GOOGLE");
			//System.out.println(requestBody);
			
			super.createINFO("DADOS CRIADOS PARA JSON");
			super.createINFO(requestBody);
			
			
			String response = tracks.requestString(ENTITY_CREATE, requestBody);
			
			
			
			//System.out.println("RESPOSTA DA GOOGLE");
			//System.out.println(response);
			
			
			EntityRetrieveIds entityRetriveIds = new EntityRetrieveIds();
			
			try {
				entityRetriveIds = gson.fromJson(response, EntityRetrieveIds.class);
				
			} catch (Exception e) {
				System.out.println(response);				
			}

			return entityRetriveIds;
			
		} catch (Exception e) {
			
			super.createERROR("ERRO NA TRANSACAO COM O GOOGLE , GoogleDAO.createEntity " );
			super.createERROR(e.getMessage());
			super.createERROR(e.getLocalizedMessage());
			super.createERROR(e.getCause().toString());
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	public EntityRetrieveIds createManyEntity(Entity1 entity1){
		System.out.println("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
		
		try {
			super.createINFO("INICIO DA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY");
			
			Gson gson = new Gson();
			
			EntityCreate create = new EntityCreate();
			create.getEntities().add(entity1);
			
			
			String requestBody = gson.toJson(create);
			
			String response = tracks.requestString(ENTITY_CREATE, requestBody);
			
			
			EntityRetrieveIds entityRetriveIds = new EntityRetrieveIds();
			entityRetriveIds = gson.fromJson(response, EntityRetrieveIds.class);
			
			
			return entityRetriveIds;
			
		} catch (Exception e) {
			
			super.createERROR("ERRO NA TRANSACAO COM O GOOGLE , CADASTRANDO ENTITY :" + e.getMessage() );			
			return null;
		}
	}
	
	public boolean removeEntity(String entityId){
		
		
		try {
			super.createINFO("INICIO DA TRANSACAO COM O GOOGLE , DELETANDO ENTITY");
			
			EntitysDelete entitysDelete = new EntitysDelete();
			entitysDelete.getEntityIds().add(entityId);
			
			Gson gson = new Gson(); 
			String requestBody = gson.toJson(entitysDelete);
			//System.out.println(requestBody);
			String response = tracks.requestString(ENTITY_DELETE, requestBody);
			
			//System.out.println(response);
			
			if(response.trim().equals("{}")){
				super.createINFO("Deletado com Sucesso na Google");
				return true;
			} else {
				super.createERROR("Erro ao deletar na Google");
				return false;
			}
			
		} catch (Exception e) {
			super.createERROR("ERRO NA TRANSACAO COM O GOOGLE , REMOVENDO ENTITY :" + e.getMessage() );			
			return false;
		}
	}
	
	public EntitysRetrieve findAllByMindId(String mindId){
		try {
			
			MinId minId = new MinId(mindId);
			Gson gson = new Gson();
			
			String requestBody = gson.toJson(minId);
			String response = tracks.requestString(ENTITY_LIST, requestBody);
			
			EntitysRetrieve entityRetrive = new EntitysRetrieve();
			entityRetrive = gson.fromJson(response, EntitysRetrieve.class);
			
			return entityRetrive;
			
		} catch (Exception e) {
			logDAO.createERROR("ERROR GoogleDAO.findAllByMindId");
			logDAO.createERROR(e.getMessage());
			return null;
		}
	}
	
	public EntitysRetrieve findAllByEntityId(String entityId){
		System.out.println("INICIO DA TRANSACAO COM O GOOGLE , BUSCANDO COMO ENTITY ID");
		try {
			
			EntityId id = new EntityId(entityId);
			Gson gson = new Gson();
			
			String requestBody = gson.toJson(id);
			System.out.println(requestBody);
			String response = tracks.requestString(ENTITY_LIST, requestBody);
			System.out.println(response);
			EntitysRetrieve entitysRetrive = new EntitysRetrieve();
			entitysRetrive = gson.fromJson(response, EntitysRetrieve.class);
			
			return entitysRetrive;
		} catch (Exception e) {
			logDAO.createERROR("ERROR no GoogleDAO.findAllByEntityID");
			logDAO.createERROR(e.getMessage());
			return null;
		}
	}
	
	public EntitysRetrieve findAllByEmpty(){
		System.out.println("INCIO DA TRANSACAO COM O GOOGLE, BUSCANDO COMO VAZIO");
		try {
			Gson gson = new Gson();
			
			String response = tracks.requestString(ENTITY_LIST, "");
			
			EntitysRetrieve entitysRetrive = new EntitysRetrieve();
			entitysRetrive = gson.fromJson(response,EntitysRetrieve.class);
			
			return entitysRetrive;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Gravando no Crumbs
	 * 
	 */
	
	public boolean recordingCrumbs(Crumbs crumbs, String entityId){
		try {
			
			Gson gson = new Gson();
			CrumbsRecording recording = new CrumbsRecording();
			recording.getCrumbs().add(crumbs);
			recording.setEntityId(entityId);
			
			String json = gson.toJson(recording);
			String response = new TracksServices().requestString(CRUMBS_RECORD, json);
			
			if(response.trim().equals("{}")){
				logDAO.createINFO("Foi cadastrado um Crumbs do id:" + entityId);
				return true;
			} else {
				Error error = new Error();
				error = gson.fromJson(response, Error.class);
				System.out.println(error.getCode() + "  -  " + error.getMessage());
				logDAO.createERROR("Perca de conexão ou algum erro com a transação do Crumbs com o Id :"+entityId);
				return false;
			}
			
		} catch (Exception e) {
			logDAO.createERROR("Error no GoogleDAO.recordingCrumbs");
			logDAO.createERROR(e.getMessage());
			return false;
		}
	}
	
	public CrumbsRecording retrieveCrumbsHistory(CrumbsRetrieveHistory crumbsRetrieveHistory){
		try {
			
			Gson gson = new Gson();
			String json = gson.toJson(crumbsRetrieveHistory);
			
			TracksServices services = new TracksServices();
			String response = services.requestString(CRUMBS_GET_HISTORY, json);
			
			CrumbsRecording crumbsRecording = new CrumbsRecording();
			crumbsRecording = gson.fromJson(response, CrumbsRecording.class);
			
			return crumbsRecording;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean removeCrumbs(CrumbsDelete  crumbsDelete){
		try {
			
			Gson g = new Gson();
			String json = g.toJson(crumbsDelete);
			
			System.out.println(json);
			
			TracksServices service = new TracksServices();
			String response = service.requestString(CRUMBS_DELETE, json);
			
			
			System.out.println("Resposta");
			if(response.trim().equals("{}")){
				System.out.println("OK -- Deletado com Sucesso");
				return true;
			} else {
				Error error = g.fromJson(response, Error.class);
				System.out.println(error.getCode());
				System.out.println(error.getMessage());
				return false;
			}
			
			
			
		} catch (Exception e) {
			return false;
		}
	}
	
}
