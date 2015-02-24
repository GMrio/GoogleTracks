package googletracks.run;

import googletracks.controller.CrumbsController;
import googletracks.controller.DadosJsonController;
import googletracks.controller.EntityController;
import googletracks.controller.GoogleController;
import googletracks.controller.HelperController;
import googletracks.controller.StartUpController;
import googletracks.dao.LogDAO;
import googletracks.model.DatabaseFile;



/**
 * 
 * 
 * @author u6448938 / Marcos Felipe
 * LENDO ARQUIVO JSON DADO PELO BETO 
 *
 *
 */

public class MainRunning {
	
	
	LogDAO logDAO = new LogDAO();
	
	public static void main(String[] args) {	
		
		if(args.length == 0){
			System.out.println("Digite uma operacao.");
			
		} else if (args.length == 1){
			menu(args[0]);
			
		} else if (args.length == 2){
			menu(args[0], args[1]);
			
		} else if(args.length == 5) {
			menu(args[0],args[1],args[2],args[3], args[4]);
			
		} else if(args.length == 3){
			menu(args[0], null, args[1] , args[2], null);
		
		} else if(args.length == 4){
			menu(args[0],args[1] , args[2], args[3],null);
		}else{
			
			System.out.println("Comando totalmente invalido !");
		}
		
	}
	
	public static void menu (String arg1, String arg2, String arg3, String arg4, String arg5){ //0,null,1,2,null
		try {
			CrumbsController crumbsController = new CrumbsController();
			
			
//			System.out.println(arg1);
//			System.out.println(arg2);
//			System.out.println(arg3);
//			System.out.println(arg4);
//			System.out.println(arg5);
			
			
			if(arg1 == null){
				arg1 = "";
			} else if (arg2 == null){
				arg2 = "";
			} else if (arg3 == null){
				arg3 = "";
			} else if (arg4 == null){
				arg4 = "";
			} else if (arg5 == null){
				arg5 = "";
			} else {
				
			}
			
			
			if(arg1.equals("retrieveCrumbsHistory")){
				
				
				
				if(arg2.equals("dayBy")){
					crumbsController.retrieveCrumbsHistoryDayById(arg3, arg4, arg5);
					arg5 = "9999";
					
				} else if (arg2.equals("") && !arg4.trim().equals("x")){ // entity e timestamp 
					crumbsController.retrieveCrumbsHistory(arg3, arg4, null, null);// entityId, timestamp, before, after
					
				} else if (arg2.equals("") && arg4.equals("x")){ // noTelefone X
					crumbsController.retornaUltimosCrumbsHistory(arg3); // noTelefone
				}
				
				int count = Integer.parseInt(arg5);
				
				if(count <= 512 || count == 0){
					 if(arg2.equals("after")){
						crumbsController.retrieveCrumbsHistory(arg3, arg4, null , arg5);
					} else if ( arg2.equals("before")){
						crumbsController.retrieveCrumbsHistory(arg3, arg4, arg5 , null);
					} else {
						System.out.println("Valor indesejado");
					}
				} else if(count == 9999){ 
					
				}else {
					System.out.println("Count tem que ser menos que 512");
				}
				
			} 
			
			
			
			
			else if(arg1.equals("deleteCrumbs")){
				if (arg2.trim().equals("") || arg3.trim().equals("") || arg4.trim().equals("")){
					System.out.println("coloque as informações necessarias = >  noTelefone minTimestamp maxTimestamp");
				} else {
					crumbsController.deletarCrumbs(arg2, arg3, arg4);
				}
			}
			
			
			
			
			else {
				System.out.println("Comando não válido");
			}
			
			
		} catch (Exception e) {
			
		}
	}
	
	public static void menu (String arg1){
	
		Actions ac = new Actions();
		StartUpController startUpController = new StartUpController();
		LogDAO logDAO = new LogDAO();
		try {
			/**
			 * PRODUÇÃO
			 */
			if(arg1.equals("deleteAll")){
				ac.deleteAll();
			}
			if(arg1.equals("install")){
				startUpController.startup();
				logDAO.createINFO("Iniciado com Sucesso");
			}
			
			if(arg1.trim().equals("help") || arg1.trim().equals("-h")){
				HelperController controller = new HelperController();
				controller.helper();
			}
			
			
			/**
			 * ERROS APENAS COM 1
			 */
			if(arg1.equals("createEntity")){
				System.out.println("Sera necessario mais 1 argumento");
				System.out.println(" createEntity <doc, numero>");
			}
			
			if(arg1.equals("findIdGoogle")){
				System.out.println("Sera necessario mais 1 argumento");
				System.out.println(" findIdGoogle <numero>");
			}
			
			if(arg1.equals("createCrumbs")){
				System.out.println("Sera necessario mais 1 argumento");
	
			}
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	public static void menu (String arg1, String arg2){
		
		EntityController entityController = new EntityController();
		DadosJsonController dadosJsonController = new DadosJsonController();
		GoogleController googleController = new GoogleController();
		CrumbsController crumbsController = new CrumbsController();
		
			/**
		 	* CreateEntity
		 	*/
		if(arg1.equalsIgnoreCase("createEntity")){
			
			if(arg2.equalsIgnoreCase("listDadosJson")){
				entityController.createEntityByDadosJson();
				
			} else if (arg2.equals("listDataDontSend")){
				entityController.createEntityDadosNaoEnviados();
				
			}
		
			
			
			/**
			 * FindAll
			 */	
		} else if(arg1.equals("findAll")){
			
			if(arg2.equals("entity")){
				entityController.findAllEntity();
				
			} else if(arg2.equals("dontCreateEntity")){
				dadosJsonController.findAllDontCreateEntity();
			
			} else if(arg2.equals("crumbsDontSend")){
				crumbsController.findAllDontSend();
			}
		
			
			/**
			 * FindIdGoogle
			 */	
		} else if(arg1.equals("findIdGoogleByNoTelefone")) {
			
			if(!(arg2 == null) || !arg2.equals("")){
				String idGoogle = entityController.findIdGoogleByNoTelefone(arg2);
				if(idGoogle == null || idGoogle.equals("")){
					System.out.println("noTelefone nao existe");
				}
				System.out.println("idGoogle:"+idGoogle);
			}
		
			/**
			 * FindNoTelefone
			 */	
		} else if(arg1.equals("findNoTelefoneByIdGoogle")) {
			
			
			if(arg2 == null || arg2.trim().equals("")){
				System.out.println("Digite um entityId");
			} else {
				googleController.findNoTelefoneByID(arg2);
			}
			
			/*if(!(arg2 == null) || !arg2.equals("") ){
				String noTelefone = googleController.findNoTelefoneByID(arg2);
				if(noTelefone == null ||noTelefone.equals("")){
					noTelefone = "null";
				}
				System.out.println("noTelefone:"+noTelefone);
			} else {
				System.out.println("Digite um entityID");
			}*/
		
		
		/**
		 * Add Telefone
		 */
		} else if(arg1.equals("addEntity")){
			
			if(arg2.equals("") || arg2 == null){
				System.out.println("Adicione um numero");
			} else {
				entityController.createByTelefone(arg2);
			}
			
			
			/**
			 * Delete Telefone
			 */	
		} else if (arg1.equals("deleteEntity")){
			
			if(arg2.equals("") || arg2 == null){
				System.out.println("Adicione um numero");
			} else {
				
				entityController.removeEntity(arg2);
			}
			
			
		} 
		
		
		
		
		/**
	 	* Create Crumbs
	 	*/
		else  if(arg1.equalsIgnoreCase("recordCrumbs")){
		
			if(arg2.equalsIgnoreCase("listDadosJson")){
				crumbsController.recordCrumbsByDadosJson();;
				
			} else if (arg2.trim().equals("")){
				System.out.println("Digite como sera incluido");
				
			} else if (arg2.equals("listDadosJsonDontSend")){
				crumbsController.recordCrumbsByDadosJsonDontSendCrumb();
			}
		
		}
		
	}

	
	
}
