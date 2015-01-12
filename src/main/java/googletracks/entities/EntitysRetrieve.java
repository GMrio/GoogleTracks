package googletracks.entities;

import googletracks.model.Entity2;

import java.util.List;

public class EntitysRetrieve {

	private List<Entity2> entities;
	
	public EntitysRetrieve() {
		// TODO Auto-generated constructor stub
	}
	public List<Entity2> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity2> entities) {
		this.entities = entities;
	}
	@Override
	public String toString() {
		return "RetriveEntitys [entities=" + entities + "]";
	}
	
	
	
}


