package googletracks.run;

import googletracks.entities.EntitysDelete;
import googletracks.entities.EntitysRetrieve;
import googletracks.model.Entity2;
import googletracks.services.TracksServices;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class DeleteAll {
	public static void main(String[] args) throws Exception {
		
		Gson gson = new Gson();
		TracksServices tacksServices = new TracksServices();
		
		String jsonString = tacksServices.requestString("entities/list", "");
		//System.out.println(jsonString);
		EntitysRetrieve entityRetrive = new EntitysRetrieve();
		List<Entity2> entity2 = new ArrayList<Entity2>();
		entityRetrive.setEntities(entity2);
		entityRetrive = gson.fromJson(jsonString, EntitysRetrieve.class); 
		System.out.println(entityRetrive);
		////////END //////////////////////
		
		
		////DELETE/////
		EntitysDelete entitysDelete = new EntitysDelete();
		int size = entityRetrive.getEntities().size();
		System.out.println(size);
		for(int i = 0 ; i < size ; i++){
			entitysDelete.getEntityIds().add(entityRetrive.getEntities().get(i).getId() );
		}
		String idsDelete = gson.toJson(entitysDelete);
		System.out.println(idsDelete);
		jsonString = tacksServices.requestString("entities/delete", gson.toJson(entitysDelete));
		System.out.println(jsonString);
		
		
	}
}
