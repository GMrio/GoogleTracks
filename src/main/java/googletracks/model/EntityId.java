package googletracks.model;

public class EntityId {

	private String entityIds;

	public EntityId(String entityIds) {
		super();
		this.entityIds = entityIds;
	}
	
	public EntityId() {
		// TODO Auto-generated constructor stub
	}

	public String getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(String entityIds) {
		this.entityIds = entityIds;
	}

	@Override
	public String toString() {
		return "EntityId [entityIds=" + entityIds + "]";
	}

	
	
	
}
