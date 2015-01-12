package googletracks.entities;

import googletracks.model.Crumbs;

import java.util.ArrayList;
import java.util.List;

public class CrumbsRecording {
	private List<Crumbs> crumbs = new ArrayList<Crumbs>();
	private String entityId;
	public CrumbsRecording() {
		// TODO Auto-generated constructor stub
	}
	
	public CrumbsRecording(List<Crumbs> crumbs, String entityId) {
		super();
		this.crumbs = crumbs;
		this.entityId = entityId;
	}

	public List<Crumbs> getCrumbs() {
		return crumbs;
	}
	public void setCrumbs(List<Crumbs> crumbs) {
		this.crumbs = crumbs;
	}
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		return "RecordingCrumbs [crumbs=" + crumbs + ", entityId=" + entityId
				+ "]";
	}
	
}
