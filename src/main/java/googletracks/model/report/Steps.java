package googletracks.model.report;

public class Steps {
	private String distance;
	private String startTimestamp;
	private String endTimestamp;
	private Address address;
	public Steps() {
		// TODO Auto-generated constructor stub
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public String getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Steps [distance=" + distance + ", startTimestamp="
				+ startTimestamp + ", endTimestamp=" + endTimestamp
				+ ", address=" + address + "]";
	}
}
