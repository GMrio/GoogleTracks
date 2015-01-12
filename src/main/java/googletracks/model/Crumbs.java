package googletracks.model;


public class Crumbs {

	private String confidenceRadius;
	private Location location = new Location();
	private String timestamp;
	private UserData userData = new UserData();
		
	public Crumbs() {
		// TODO Auto-generated constructor stub
	}
	public String getConfidenceRadius() {
		return confidenceRadius;
	}
	public void setConfidenceRadius(String confidenceRadius) {
		this.confidenceRadius = confidenceRadius;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	@Override
	public String toString() {
		return "Crumbs [confidenceRadius=" + confidenceRadius + ", location="
				+ location + ", timestamp=" + timestamp + ", userData="
				+ userData + "]";
	}
	
	
}
