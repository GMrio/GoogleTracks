package googletracks.model.report;

import java.util.List;

public class Report {
	private List<Trips> trips;
	public Report() {
		// TODO Auto-generated constructor stub
	}
	public List<Trips> getTrips() {
		return trips;
	}
	public void setTrips(List<Trips> trips) {
		this.trips = trips;
	}
	@Override
	public String toString() {
		return "Report [trips=" + trips + "]";
	}
	
}
