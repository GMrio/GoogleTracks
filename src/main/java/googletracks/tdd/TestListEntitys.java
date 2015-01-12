package googletracks.tdd;

import googletracks.entities.EntityCreate;
import googletracks.entities.EntityRetrieveIds;
import googletracks.model.Entity1;
import googletracks.model.TypeEntity;
import googletracks.services.TracksServices;

import com.google.gson.Gson;

public class TestListEntitys {
	public static void main(String[] args) {
		
		Gson gson = new Gson();
		
		Entity1 entity1 = new Entity1();
		entity1.setName("Marcos");
		entity1.setType(TypeEntity.PERSON);
		
		
		EntityCreate create = new EntityCreate();
		create.getEntities().add(entity1);
		
		
		String requestBody = gson.toJson(create);
		
		TracksServices tracks = new TracksServices();
		
		System.out.println(requestBody);
		
		String response = tracks.requestAuthJson("entities/create", requestBody);
		
		
		EntityRetrieveIds entityRetriveIds = new EntityRetrieveIds();
		entityRetriveIds = gson.fromJson(response, EntityRetrieveIds.class);
		
		
		System.out.println(entityRetriveIds.getEntityIds());
		
	}

}
