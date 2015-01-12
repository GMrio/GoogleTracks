package googletracks.entities;


public class CrumbsDelete {

	private String entityId;
	
	private Long maxTimestamp;
	
	private Long minTimestamp;
	
	public CrumbsDelete() {
		
	}
	

	public CrumbsDelete(String entityId, Long maxTimestamp, Long minTimestamp) {
		this.entityId = entityId;
		this.maxTimestamp = maxTimestamp;
		this.minTimestamp = minTimestamp;
	}


	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Long getMaxTimestamp() {
		return maxTimestamp;
	}

	public void setMaxTimestamp(Long maxTimestamp) {
		this.maxTimestamp = maxTimestamp;
	}

	public Long getMinTimestamp() {
		return minTimestamp;
	}

	public void setMinTimestamp(Long minTimestamp) {
		this.minTimestamp = minTimestamp;
	}


	@Override
	public String toString() {
		return "CrumbsDelete [entityId=" + entityId + ", maxTimestamp="
				+ maxTimestamp + ", minTimestamp=" + minTimestamp + "]";
	}
	
	
	
	
}
