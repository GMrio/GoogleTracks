package googletracks.model.report;

import googletracks.model.Location;

import java.util.List;

public class Trips {
	
	private Location startLocation;
	private Location endLocation;
	private String startTimestamp;
	private String endTimestamp;
	private String distance;
	private List<Stopovers> stopovers;
	private List<Event> events;
	private List<Steps> steps;
	
	public Trips() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Stopovers> getStopovers() {
		return stopovers;
	}


	public void setStopovers(List<Stopovers> stopovers) {
		this.stopovers = stopovers;
	}


	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}


	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public List<Steps> getSteps() {
		return steps;
	}


	public void setSteps(List<Steps> steps) {
		this.steps = steps;
	}


	@Override
	public String toString() {
		return "Trips [startLocation=" + startLocation + ", endLocation="
				+ endLocation + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + ", distance=" + distance
				+ ", stopovers=" + stopovers + ", events=" + events
				+ ", steps=" + steps + "]";
	}

	
	
}
