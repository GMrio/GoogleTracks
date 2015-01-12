package googletracks.entities;

public class CrumbsRetrieveHistory {
	
	private String entityId;
	private String timestamp;
	private String countBefore;
	private String countAfter;
	
	public CrumbsRetrieveHistory() {
		// TODO Auto-generated constructor stub
	}
	
	public CrumbsRetrieveHistory(String entityId, String timestamp,
			String countBefore, String countAfter) {
		super();
		this.entityId = entityId;
		this.timestamp = timestamp;
		this.countBefore = countBefore;
		this.countAfter = countAfter;
	}


	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCountBefore() {
		return countBefore;
	}

	public void setCountBefore(String countBefore) {
		this.countBefore = countBefore;
	}

	public String getCountAfter() {
		return countAfter;
	}

	public void setCountAfter(String countAfter) {
		this.countAfter = countAfter;
	}


	@Override
	public String toString() {
		return "CrumbsRetrieveHistory [entityId=" + entityId + ", timestamp="
				+ timestamp + ", countBefore=" + countBefore + ", countAfter="
				+ countAfter + "]";
	}
	
	

}
