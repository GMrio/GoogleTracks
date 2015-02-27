package googletracks.controller;

import googletracks.services.TracksServices;

public class ExecController {

	public void exec(String metodo , String requestBody) {
		
		System.out.println(metodo);
		System.out.println(requestBody);
		
		try {
			TracksServices services = new TracksServices();
			String retorno = services.requestString(metodo, requestBody);
			
			
			System.out.println(retorno);
			
		} catch (Exception e) {
			
		}
		
	}
}
