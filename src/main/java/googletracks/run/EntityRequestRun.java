package googletracks.run;

import googletracks.entities.EntityCreate;
import googletracks.entities.EntitysDelete;
import googletracks.model.Entity1;
import googletracks.model.EntityId;
import googletracks.model.MinId;
import googletracks.model.TypeEntity;

import com.google.gson.Gson;


/****
 * 
 * @author u6448938 / Marcos Felipe
 *
 *	Classe para testar os envios para google: 
 *  
 *
 */
public class EntityRequestRun {
	
	public static void main(String[] args) {
		
		String listAll = "";
		MinId mindId = new MinId("15d4a4a21sa");
		EntityId entityId = new EntityId("12155512222a");
		Entity1 entity1 = new Entity1("Marcos", TypeEntity.PERSON);
		
		String id1 = "asdfaskfjas1";
		String id2 = "dasdsaddas";
		String id3 = "dasda113sa";
		
		EntitysDelete entitysDelete = new EntitysDelete();
		entitysDelete.getEntityIds().add(id1);
		entitysDelete.getEntityIds().add(id2);
		entitysDelete.getEntityIds().add(id3);
		
		EntityCreate entityCreate = new EntityCreate();
		entityCreate.getEntities().add(entity1);
		
		Gson gson = new Gson();
	
		/*
		 * System pode receber : listAll, mindId, entityId, entity1, entityCreate, entitysDelete;
		 */
		System.out.println(gson.toJson(mindId));
	}
	
	
}
