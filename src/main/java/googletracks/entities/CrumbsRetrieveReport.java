package googletracks.entities;

import googletracks.model.report.Report;

public class CrumbsRetrieveReport {
	private Report report;
	public CrumbsRetrieveReport() {
		// TODO Auto-generated constructor stub
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	@Override
	public String toString() {
		return "CrumbsRetrieveReport [report=" + report + "]";
	}
	
}
