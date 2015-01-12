package googletracks.entities;

import googletracks.model.Entity1;

import java.util.ArrayList;
import java.util.List;

public class EntityCreate {
	
	private List<Entity1> entities = new ArrayList<Entity1>();
	public EntityCreate() {
		// TODO Auto-generated constructor stub
	}
	public List<Entity1> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity1> entities) {
		this.entities = entities;
	}
	@Override
	public String toString() {
		return "EntityCreate [entities=" + entities + "]";
	}
	
}
