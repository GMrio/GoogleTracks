package googletracks.entities;

import java.util.ArrayList;
import java.util.List;

public class EntitysDelete {
	private List<String> entityIds = new ArrayList<String>();
	public EntitysDelete() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(List<String> entityIds) {
		this.entityIds = entityIds;
	}

	@Override
	public String toString() {
		return "EntitysDelete [entityIds=" + entityIds + "]";
	}
	
}
