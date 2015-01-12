package googletracks.controller;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.LogDAO;
import googletracks.entities.DadosJsonVO;
import googletracks.model.DadosJson;

public class DadosJsonController {
	
	
	private LogDAO logDAO = new LogDAO();
	
	/**************************************************
	 * TRAZENDO TODAS QUE NÃO CONSEGUIRAM SER CRIADA
	 **************************************************/
	public void findAllDontCreateEntity(){
		
		try {
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DadosJsonVO documentsJonsVO = new DadosJsonVO();
			documentsJonsVO = dadosJsonDAO.findAllDadosJsonNotSend();
			
			for(DadosJson doc : documentsJonsVO.getCrumbs()){
				System.out.println("noTelefone:" + doc.getNoTelefone());
			}
			logDAO.createINFO("Trazendo todas as que não conseguiram ser enviada.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
