package googletracks.controller;

import googletracks.dao.GoogleDAO;
import googletracks.dao.LogDAO;
import googletracks.entities.EntitysRetrieve;

public class GoogleController {

	private LogDAO logDAO = new LogDAO();
	
	
	public void findNoTelefoneByID(String idGoogle){
		try {
			GoogleDAO googleDAO = new GoogleDAO();
			EntitysRetrieve entitysRetrive = googleDAO.findAllByMindId(idGoogle);
			String result = entitysRetrive.getEntities().get(0).getName();
			if(result.trim().equals("") || result == null){
				result = "null";
			}
			System.out.println(result);
			
		} catch (Exception e) {
			logDAO.createERROR("Erro no GoogleController.findNoTelefoneByID");
			logDAO.createERROR(e.getMessage());
			System.out.println("entityId =null");
		}
	}
	
	
	public boolean removeIdGoogle(String idGoogle){
		GoogleDAO googleDAO = new GoogleDAO();
		boolean check = false;
		try {
			check = googleDAO.removeEntity(idGoogle);
			if(check){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logDAO.createERROR("Error no GoogleController.removeIdGoogle");
			logDAO.createERROR(e.getMessage());
			return false;
		}
	}
}
