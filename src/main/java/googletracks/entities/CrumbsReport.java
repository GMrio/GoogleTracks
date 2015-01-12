package googletracks.entities;

public class CrumbsReport {
	private String entityId;
	private Long minTimestamp;
	private Long maxTimestamp;
	
	
	public CrumbsReport() {
		// TODO Auto-generated constructor stub
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	

	public Long getMinTimestamp() {
		return minTimestamp;
	}

	public void setMinTimestamp(Long minTimestamp) {
		this.minTimestamp = minTimestamp;
	}

	public Long getMaxTimestamp() {
		return maxTimestamp;
	}

	public void setMaxTimestamp(Long maxTimestamp) {
		this.maxTimestamp = maxTimestamp;
	}

	@Override
	public String toString() {
		return "CrumbsDelete [entityId=" + entityId + ", maxTimestamp="
				+ maxTimestamp + ", minTimestamp=" + minTimestamp + "]";
	}
	
}
