package googletracks.tdd;

import com.google.gson.Gson;

import googletracks.controller.CrumbsController;
import googletracks.dao.GoogleDAO;
import googletracks.entities.CrumbsRecording;
import googletracks.entities.CrumbsReport;
import googletracks.entities.CrumbsRetrieveHistory;
import googletracks.entities.CrumbsRetrieveReport;
import googletracks.model.Crumbs;
import googletracks.model.Location;
import googletracks.model.UserData;
import googletracks.services.TracksServices;


public class MainzinhaCrumbs {
	public static void main(String[] args) {
		
		Gson gson = new Gson();
		
//		CrumbsRecording crumbsRecording = new CrumbsRecording();
//		crumbsRecording.setEntityId("12afa23f1");
//		
//		Crumbs crumbs = new Crumbs();
//		crumbs.setConfidenceRadius("23423423");
//		crumbs.setTimestamp("123.12312");
//		
//		Location location = new Location("1231", "-12312");
//		crumbs.setLocation(location);
//		
//		UserData userData = new UserData("1", "24", "0", "21314487331", "0");
//		crumbs.setUserData(userData);
//		
//		crumbsRecording.getCrumbs().add(crumbs);
//		
//		String json = gson.toJson(crumbsRecording);
//		
//		System.out.println(json);
		
		CrumbsReport crumbsReport = new CrumbsReport();
		crumbsReport.setEntityId("dea788438fd29753");
		crumbsReport.setMaxTimestamp((long)1414579700);
		crumbsReport.setMinTimestamp((long)1414595012);
		
		String json = gson.toJson(crumbsReport);
		System.out.println(json);
		
		TracksServices tracks = new TracksServices();
		String retorno = tracks.requestString("crumbs/report", json);
		
		CrumbsRetrieveReport crumbsRetrieveReport = new CrumbsRetrieveReport();
		crumbsRetrieveReport = gson.fromJson(json, CrumbsRetrieveReport.class);
		
		System.out.println(crumbsRetrieveReport.getReport());
		
	}
}
