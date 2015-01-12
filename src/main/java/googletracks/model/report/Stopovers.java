package googletracks.model.report;

import googletracks.model.Location;

public class Stopovers {
	private Location location;
	private String startTimestamp;
	private String endTimestamp;
	private Address address;
	public Stopovers() {
		// TODO Auto-generated constructor stub
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
		return "Stopovers [location=" + location + ", startTimestamp="
				+ startTimestamp + ", endTimestamp=" + endTimestamp
				+ ", address=" + address + "]";
	}
}
