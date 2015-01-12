package googletracks.run;

import googletracks.entities.EntityRetrieveIds;
import googletracks.entities.EntitysRetrieve;

import com.google.gson.Gson;

public class EntityResponseRun {
	public static void main(String[] args) {
	
		String responseCreate = "{ \"entityIds\": [ \"ec6053f142ade5c9\", \"1ff3a55f94e954ee\", \"fb061e749fec1627\" ] }";
		
		String responseList = "{ \"entities\": [     "
				+ "{ \"id\": \"1ff3a55f94e954ee\", "
				+ "\"name\": \"Chevrolet Volt 001\", "
				+ "\"type\": \"AUTOMOBILE\" }, "
				+ "{ \"id\": \"ec6053f142ade5c9\", "
				+ "\"name\": \"Ford Fiesta 001\",  "
				+ "\"type\": \"AUTOMOBILE\"  } "
				+ " ]}";
		
		String responseDelete = "{}";
		
		
		Gson gson = new Gson();
		
		
		EntitysRetrieve entitysRetrive = new EntitysRetrieve();
		entitysRetrive = gson.fromJson(responseList, EntitysRetrieve.class);
		
		EntityRetrieveIds entityRetriveIds = new EntityRetrieveIds();
		entityRetriveIds = gson.fromJson(responseCreate, EntityRetrieveIds.class);
		
		//System.out.println(entitysRetrive);
		
		System.out.println(entityRetriveIds);
	
	}

}
