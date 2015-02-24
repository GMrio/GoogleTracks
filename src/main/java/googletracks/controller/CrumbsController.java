package googletracks.controller;

import googletracks.dao.DadosJsonDAO;
import googletracks.dao.DatabaseFileDAO;
import googletracks.dao.GoogleDAO;
import googletracks.dao.LogDAO;
import googletracks.entities.CrumbsDelete;
import googletracks.entities.CrumbsRecording;
import googletracks.entities.CrumbsRetrieveHistory;
import googletracks.entities.DadosJsonVO;
import googletracks.model.Crumbs;
import googletracks.model.DadosJson;
import googletracks.model.Location;
import googletracks.model.UserData;
import googletracks.regras.NoTelefoneRegras;
import googletracks.utils.Converts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;


public class CrumbsController {
	
	private LogDAO logDAO = new LogDAO();
	
	public void findAllDontSend(){
		try {
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DadosJsonVO dadosJsonVO = new DadosJsonVO();
			dadosJsonVO = dadosJsonDAO.findAllDadosJsonDontSendCrumbs();
			
			for(DadosJson dados : dadosJsonVO.getCrumbs()){
				System.out.println("noTelefone:" + dados.getNoTelefone());
				System.out.println("imei:" + dados.getImei());
				System.out.println("precisao:" + dados.getPrecisao());
				System.out.println("idEstado:" + dados.getIdEstado());
				System.out.println("logado:" + dados.getLogado());
				System.out.println("gps:" + dados.getGps());
				System.out.println("---");
			}
			logDAO.createINFO("Listando todos os Crumbs não enviados");
		} catch (Exception e) {
			System.out.println("Não teve arquivo");
		}
	}
	
	public void recordCrumbsByDadosJson(){
		
		try {
			
			CrumbsRecording crumbsRecording = new CrumbsRecording();
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DadosJsonVO dadosJsonVO = new DadosJsonVO();
			GoogleDAO googleDAO = new GoogleDAO();
			
			//Lendo os dados do dados.json
			dadosJsonVO = dadosJsonDAO.findAllDadosJson();
			
			//Coletando dados por dados
			for (DadosJson dados : dadosJsonVO.getCrumbs()){
				System.out.println("==================================");
				DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
				
				//verifica se existe o idGoogle no Database (arquivo)
				String idGoogle = databaseFileDAO.findByIdGoogleFromNoTelefone(dados.getNoTelefone());
				
				if(idGoogle == null || idGoogle.equals("")){ 
					System.out.println("noTelefone" + dados.getNoTelefone() + " nao contem o EntityId");
					
					this.logDAO.createINFO("noTelefone nao contem o EntityId = " + dados.getNoTelefone());
					//throw new Exception("noTelefone nao contem o EntityId");
				
				} else {
					crumbsRecording.setEntityId(idGoogle);
				
				
				
					/**
					 *Setando os dados resgatados acima
					 */
					Crumbs crumbs = new Crumbs();
					crumbs.setConfidenceRadius(dados.getPrecisao());
					crumbs.setTimestamp(dados.getData());
					
					Location location = new Location(dados.getLatitude(), dados.getLogado());
					crumbs.setLocation(location);
					
					Integer precisao = 999;
					
					
					
					//System.out.println("GET DADOS.PRECISAO = " + dados.getPrecisao());
					try {
						precisao = Integer.parseInt(dados.getPrecisao());
						//System.out.println("try precisao = " + precisao);
					} catch (Exception e) {
						//System.out.println("catch precisao = " + precisao);
						dados.setPrecisao(precisao.toString());
					}
					
					
					if(precisao <= 0){
						dados.setPrecisao("0");
					}
					
					UserData userData = new UserData(dados.getIdEstado(),
													dados.getBateria(), 
													dados.getGps(), 
													dados.getImei(), 
													dados.getLogado());
					crumbs.setUserData(userData);
					crumbsRecording.getCrumbs().add(crumbs);
					
					boolean ok = googleDAO.recordingCrumbs(crumbs, crumbsRecording.getEntityId()); //gravando crumbs
					
					
					
					if(ok){
						System.out.println("Crumb Cadastrado com sucesso ");
						System.out.println("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						logDAO.createINFO("Crumb Cadastrado com sucesso :");
						logDAO.createINFO("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						
						dadosJsonDAO.removeDadosJson(dados);
						
					} else {
						logDAO.createERROR("Erro ao gravar o crumbs");
						logDAO.createINFO("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						System.out.println("Erro ao gravar o crumbs");
						System.out.println("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						
						
						dadosJsonDAO.saveDadosJsonNotSendForCrumbs(dados);
						dadosJsonDAO.removeDadosJson(dados);
					}
				}
			}
			
			System.out.println("============================================");
			System.out.println("Tentativas dos dados que não foram inseridos");
			System.out.println("============================================");
			for(int w = 0 ; w < 3; w ++){
				System.out.println("============================================");
				System.out.println("Tentativas dos dados que não foram inseridos");
				System.out.println("----  " + w +" ----");
				System.out.println("============================================");
				
				
				recordCrumbsByDadosJsonDontSendCrumb();
			}
			
		} catch (Exception e) {
			logDAO.createERROR("ERROR no CrumbsController.recordCrumbsReadingDadosJson");
			logDAO.createERROR(e.getMessage());
			System.out.println("Ocorreu um erro informe ao suporte ");
		}
	}
	
	public void recordCrumbsByDadosJsonDontSendCrumb(){
		
		try {
			
			CrumbsRecording crumbsRecording = new CrumbsRecording();
			DadosJsonDAO dadosJsonDAO = new DadosJsonDAO();
			DadosJsonVO dadosJsonVO = new DadosJsonVO();
			GoogleDAO googleDAO = new GoogleDAO();
			
			//Lendo os dados do dados_json_nao_cadastro_crumbs.json
			dadosJsonVO = dadosJsonDAO.findAllDadosJsonDontSendCrumbs();
			
			
			for (DadosJson dados : dadosJsonVO.getCrumbs()){

				DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
				
				//verifica se existe o idGoogle 
				String idGoogle = databaseFileDAO.findByIdGoogleFromNoTelefone(dados.getNoTelefone());

				
				/**
				 *Setando os dados resgatados acima
				 */
				
			//	System.out.println("GET PRECISAO == " + dados.getPrecisao());
				
				crumbsRecording.setEntityId(idGoogle);
				
				Integer precisao = 999;
				
				
				
				System.out.println("GET DADOS.PRECISAO = " + dados.getPrecisao());
				try {
					precisao = Integer.parseInt(dados.getPrecisao());
					System.out.println("try precisao = " + precisao);
				} catch (Exception e) {
					System.out.println("catch precisao = " + precisao);
					dados.setPrecisao(precisao.toString());
					
				}
				
				if(dados.getPrecisao() == null || dados.getPrecisao().equals("null")){
					dados.setPrecisao("1");
				} else {
					
					//dados.setPrecisao(new String(precisao));
					
					precisao = Integer.parseInt(dados.getPrecisao());
				
				
					if(precisao <= 0){
						dados.setPrecisao("1");
					}
					
					
					Crumbs crumbs = new Crumbs();
					crumbs.setConfidenceRadius(dados.getPrecisao());
					crumbs.setTimestamp(dados.getData());
					
					Location location = new Location(dados.getLatitude(), dados.getLogado());
					crumbs.setLocation(location);
					
				
					
					UserData userData = new UserData(dados.getIdEstado(),
													dados.getBateria(), 
													dados.getGps(), 
													dados.getImei(), 
													dados.getLogado());
					crumbs.setUserData(userData);
					crumbsRecording.getCrumbs().add(crumbs);
					
					boolean ok = googleDAO.recordingCrumbs(crumbs, crumbsRecording.getEntityId()); //gravando crumbs
					
					if(ok){
						
						System.out.println("Crumb Cadastrado com sucesso :");
						System.out.println("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						logDAO.createINFO("Crumb Cadastrado com sucesso :");
						logDAO.createINFO("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						dadosJsonDAO.removeDadosJsonNotSendCrumbs(dados);
					} else {
						logDAO.createERROR("Erro ao gravar o crumbs");
						logDAO.createINFO("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());
						System.out.println("Ocorreu um erro ao gravar o crumbs, tente novamente !");
						System.out.println("noTelefone:" + dados.getNoTelefone() + ",idGoogle:" + crumbsRecording.getEntityId());

						//dadosJsonDAO.saveDadosJsonNotSendForCrumbs(dados);
						//dadosJsonDAO.removeDadosJsonNotSendCrumbs(dados);
					}
				
				}
			}
			
			
		} catch (Exception e) {
			logDAO.createERROR("ERROR no CrumbsController.recordCrumbsByDadosJsonDontSendCrumb");
			logDAO.createERROR(e.getMessage());
			System.out.println("Ocorreu um erro informe ao suporte ");
			e.printStackTrace();
		}
	}
	
	public void retrieveCrumbsHistory(String noTelefone, String timestamp, String before, String after){
		
		NoTelefoneRegras regras = new NoTelefoneRegras();
		
		if(regras.noTelefoneCorreto(noTelefone)){
		
		
			try {
				
				
				
				DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
				String entityId = databaseFileDAO.findByIdGoogleFromNoTelefone(noTelefone);
				
				CrumbsRetrieveHistory crumbsRetrieveHistory = new CrumbsRetrieveHistory(entityId, timestamp, before, after);
				//System.out.println(crumbsRetrieveHistory);
				
				Gson gson = new Gson();
				System.out.println(gson.toJson(crumbsRetrieveHistory));
				GoogleDAO dao = new GoogleDAO();
				CrumbsRecording crumbsRecording =  dao.retrieveCrumbsHistory(crumbsRetrieveHistory);
	//			
				
				for(Crumbs crumbs : crumbsRecording.getCrumbs()){
					System.out.println("confidenceRadius:" + crumbs.getConfidenceRadius());
					System.out.println("timestamp:" + crumbs.getTimestamp());
					System.out.println("lat:" + crumbs.getLocation().getLat());
					System.out.println("long:" + crumbs.getLocation().getLng());
					System.out.println("bateria:" + crumbs.getUserData().getBateria());
					System.out.println("gps" + crumbs.getUserData().getGps());
					System.out.println("idEstado: " + crumbs.getUserData().getIdEstado());
					System.out.println("imei:" + crumbs.getUserData().getImei());
					System.out.println("logado:" + crumbs.getUserData().getLogado());
					System.out.println("------");
					
				}
				System.out.println("--Listado " +  crumbsRecording.getCrumbs().size() + " crumbs.");
				
				
				logDAO.createINFO("Listando Historico de Crumbs");
				logDAO.createINFO(crumbsRecording.getCrumbs().toString());
				
			} catch (Exception e) {
				logDAO.createERROR("Ocorreu um erro no CrumbsController.retrieveCrumbsHistory");
				logDAO.createERROR(e.getMessage());
			}
			
		}
	}
	
	
	
	
	
	public void retornaUltimosCrumbsHistory(String noTelefone){
		
		NoTelefoneRegras regras = new NoTelefoneRegras();
		
		if(regras.noTelefoneCorreto(noTelefone)){
		
			try {
				
				EntityController entityController = new EntityController();
				
				String idGoogle = entityController.findIdGoogleByNoTelefone(noTelefone);
				
				if(idGoogle != null || !idGoogle.trim().equals("")){
					Long timestamp = System.currentTimeMillis() / 1000L;
					CrumbsRetrieveHistory crumbsRetrieveHistory = new CrumbsRetrieveHistory(idGoogle, timestamp.toString(), "512", null);
					System.out.println(crumbsRetrieveHistory);
					
					Gson gson = new Gson();
					System.out.println(gson.toJson(crumbsRetrieveHistory));
					GoogleDAO dao = new GoogleDAO();
					CrumbsRecording crumbsRecording =  dao.retrieveCrumbsHistory(crumbsRetrieveHistory);
	//				
					
					for(Crumbs crumbs : crumbsRecording.getCrumbs()){
						System.out.println("confidenceRadius:" + crumbs.getConfidenceRadius());
						System.out.println("timestamp:" + crumbs.getTimestamp());
						System.out.println("lat:" + crumbs.getLocation().getLat());
						System.out.println("long:" + crumbs.getLocation().getLng());
						System.out.println("bareia:" + crumbs.getUserData().getBateria());
						System.out.println("gps" + crumbs.getUserData().getGps());
						System.out.println("idEstado: " + crumbs.getUserData().getIdEstado());
						System.out.println("imie:" + crumbs.getUserData().getImei());
						System.out.println("logado:" + crumbs.getUserData().getLogado());
						System.out.println("------");
						
					}
					System.out.println("--Listado " +  crumbsRecording.getCrumbs().size() + " crumbs.");
					
					
					logDAO.createINFO("Listando os ultimos 512 Historico de Crumbs");
					logDAO.createINFO(crumbsRecording.getCrumbs().toString());
					logDAO.createINFO( "Total de crumbs " + crumbsRecording.getCrumbs().size());
					logDAO.createINFO(new Date().toString());
					
				} else {
					
					System.out.println("noTelefone não esta cadastrado.");
				}
				
			} catch (Exception e) {
				logDAO.createERROR("Ocorreu um erro no CrumbsController.retornaUltimosCrumbsHistory");
				logDAO.createERROR(e.getMessage());
			}
		}
	}
	
	
	
	
	
	public int verificaSeContemCrumbsHistory(String entityId, Long timestamp){
		try {
			
			//System.out.println("TIMESTAMP" + timestamp.toString());
			
			CrumbsRetrieveHistory crumbsRetrieveHistory = new CrumbsRetrieveHistory(entityId, timestamp.toString(), "500", null);
			//System.out.println(crumbsRetrieveHistory);
			
			Gson gson = new Gson();
			//System.out.println(gson.toJson(crumbsRetrieveHistory));
			GoogleDAO dao = new GoogleDAO();
			CrumbsRecording crumbsRecording =  dao.retrieveCrumbsHistory(crumbsRetrieveHistory);

			logDAO.createINFO("Verificando Historico de Crumbs");
			logDAO.createINFO(crumbsRecording.getCrumbs().toString());
			
			
			
			return crumbsRecording.getCrumbs().size();
			
		} catch (Exception e) {
			logDAO.createERROR("Ocorreu um erro no CrumbsController.verificaSeContemCrumbsHistory");
			logDAO.createERROR(e.getMessage());
			return 0;
		}
	}
	
	
												
	public void retrieveCrumbsHistoryDayById(String noTelefone, String minTimestamp, String maxTimestamp){
		try {
			
			DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
			
			String entityId = databaseFileDAO.findByIdGoogleFromNoTelefone(noTelefone);
			
			Long lastTime = Long.parseLong(minTimestamp);
			Long maxTime = Long.parseLong(maxTimestamp);
			
			boolean startWhile = true;
			
			GoogleDAO googleDAO = new GoogleDAO();
			
			Date maxDate = new Date();
			maxDate.setTime((long)maxTime* 1000);
			
			List<Crumbs> allCrumbs = new ArrayList<Crumbs>();
			
			Converts converts = new Converts();
			Date actualDate = new Date();
			
			while(startWhile){
				CrumbsRetrieveHistory crumbsRetrieveHistory = new CrumbsRetrieveHistory(entityId, lastTime.toString(), null, "511"); //Monta de acordo com a ultima data
			
				CrumbsRecording crumbsRecording = new CrumbsRecording();
				crumbsRecording = googleDAO.retrieveCrumbsHistory(crumbsRetrieveHistory);
				
				try {
					
					int x = crumbsRecording.getCrumbs().size(); //tamanho da lista de retorno
					
					
					if(x ==0){ //tamanho for 0 
						startWhile = false;
						break;
					}
					
				} catch (Exception e) {
					startWhile = false;
					break;
				}
				
				for(int i = 0; i < crumbsRecording.getCrumbs().size() ; i++){
					
					
					// Pego a data do Crumbs e coloco na data
					String crumbsTimestamp = converts.timestampGoogleForTimestamp(crumbsRecording.getCrumbs().get(i).getTimestamp());
					actualDate.setTime((long) Long.parseLong(crumbsTimestamp) * 1000);
					
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
					System.out.println("Max DAte : " + df.format(maxDate));
					
					System.out.println("Actual DAte : " + df.format(actualDate));
					System.out.println("------");
					
					if(maxDate.before(actualDate)){
						startWhile = false;
						break;
					} else {
						allCrumbs.add(crumbsRecording.getCrumbs().get(i));
						lastTime = Long.parseLong(crumbsTimestamp)*1000;
					}
				}
				
			}
			
			int size = allCrumbs.size();
			allCrumbs.remove(size-1);
			
			for(Crumbs c : allCrumbs){
				System.out.println("confidenceRadius:" + c.getConfidenceRadius());
				System.out.println("timestamp:" + c.getTimestamp());
				System.out.println("latitude:" + c.getLocation().getLat());
				System.out.println("longitude:" + c.getLocation().getLng());
				System.out.println("idEstado:" + c.getUserData().getIdEstado());
				System.out.println("bateria: " + c.getUserData().getBateria());
				System.out.println("gps: " + c.getUserData().getBateria());
				System.out.println("imei: " + c.getUserData().getImei());
				System.out.println("logado:" + c.getUserData().getLogado());
				System.out.println("----");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public void retrieveCrumbsHistoryDayById2(String noTelefone, String minTimestamp, String maxTimestamp){
		try {
			
			GoogleDAO googleDAO = new GoogleDAO();
			Converts converter = new Converts();
			
			//Transformar o noTelefone em entityId
			DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
			String entityId = databaseFileDAO.findByIdGoogleFromNoTelefone(noTelefone);
			
			//Transforma os timestamps
			Long minTime = Long.parseLong(minTimestamp);
			Long maxTime = Long.parseLong(maxTimestamp);
			
			Long dataAtual = maxTime;
			
			// Tratando os Crumbs
			List<Crumbs> crumbsResponse = new ArrayList<Crumbs>();
			
			
			
			boolean stop = true;
			
			while(stop){
				
				CrumbsRetrieveHistory crumbsHistory = new CrumbsRetrieveHistory(entityId, minTime.toString(), null, "511");
				CrumbsRecording crumbsRecording = new CrumbsRecording();
				crumbsRecording = googleDAO.retrieveCrumbsHistory(crumbsHistory);
				
				for(Crumbs crumbs : crumbsRecording.getCrumbs()){
					
					//transformar os times em data , junto com os timestamps da Google
					Long timeTrilha = Long.parseLong(converter.timestampGoogleForTimestamp(crumbs.getTimestamp()));
					
					
					Date trilhaData = new Date(timeTrilha*1000);
					Date maximo = new Date(dataAtual*1000);
					
					if(trilhaData.before(maximo)){
						crumbsResponse.add(crumbs);
						dataAtual = timeTrilha;
					} else if(trilhaData.after(maximo)){
						stop = false;
						break;
					} else {
						System.out.println("Deu ruim");
					}
					
				}
				
				crumbsHistory = new CrumbsRetrieveHistory(entityId, dataAtual.toString(), null, "511");
				
				
				
				try {
					Thread.sleep(500);
				} catch (Exception e) {
				}
			} // fim do while true
			
			

			for(Crumbs c : crumbsResponse){
				System.out.println("confidenceRadius:" + c.getConfidenceRadius());
				System.out.println("timestamp:" + c.getTimestamp());
				System.out.println("latitude:" + c.getLocation().getLat());
				System.out.println("longitude:" + c.getLocation().getLng());
				System.out.println("idEstado:" + c.getUserData().getIdEstado());
				System.out.println("bateria: " + c.getUserData().getBateria());
				System.out.println("gps: " + c.getUserData().getBateria());
				System.out.println("imei: " + c.getUserData().getImei());
				System.out.println("logado:" + c.getUserData().getLogado());
				System.out.println("----");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/*************************
	 * 
	 * 
	 * DELETAR CRUMBS
	 * 
	 **************************/
	
	public void deletarCrumbs(String noTelefone, String minTimestamp, String maxTimestamp){
		System.out.println(noTelefone + minTimestamp + maxTimestamp);
		
		NoTelefoneRegras regras = new NoTelefoneRegras();
		
		if(regras.noTelefoneCorreto(noTelefone)){
		
			try {
				DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
				String entityId = databaseFileDAO.findByIdGoogleFromNoTelefone(noTelefone);
				
				Long min = Long.parseLong(minTimestamp);
				Long max = Long.parseLong(maxTimestamp);
				
				CrumbsDelete crumbsDelete = new CrumbsDelete(entityId, max, min);
				
				
				GoogleDAO googleDao = new GoogleDAO();
				boolean check = googleDao.removeCrumbs(crumbsDelete);
				
				if(check){
					System.out.println("Crumbs deletado com Sucesso");
					this.logDAO.createINFO("Crumbs deletado com Sucesso");
					this.logDAO.createINFO(crumbsDelete.toString());
				} 
				
			} catch (Exception e) {
				this.logDAO.createERROR("ERROR CrumbsController.deletarCrumbs()");
				this.logDAO.createERROR(e.getMessage());
			}
		}
		
	}
	
	
	
	
}
