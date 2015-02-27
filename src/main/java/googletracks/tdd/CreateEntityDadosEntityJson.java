package googletracks.tdd;

import googletracks.controller.EntityController;

public class CreateEntityDadosEntityJson {
	public static void main(String[] args) {
		
		
		System.getProperties().put("proxySet", "true");
	    System.getProperties().put("proxyHost", "10.2.118.64"); //proxy.rio.rj.gov.br  10.2.118.64
	    System.getProperties().put("proxyPort", "8080");
		
		EntityController controller = new EntityController();
		controller.criarEntitityPeloDadosEntityJson();
	}
}
