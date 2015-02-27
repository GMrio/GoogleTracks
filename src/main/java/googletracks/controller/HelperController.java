package googletracks.controller;

public class HelperController {
	
	//
	
	public void helper(){
		System.out.println("===================== MENU ===================================");
		System.out.println("-install  --para iniciar a API, apenas rode na primeira vez");
		System.out.println("");
		System.out.println("");
		System.out.println("-findAll dontCreateEntity  --mostra todos os EntityId nao cadastrado na Google quando cadastrou pelo dados.json");
		System.out.println("-findAll entity  --lista todas as entityIds e seu noTelefone");
		System.out.println("-findAll crumbsDontSend  --todos os crumbs nao enviado");
		System.out.println("");
		System.out.println("");
		System.out.println("-findIdGoogleByNoTelefone <noTelefone>  -- mostra o entityId do numero informado");
		System.out.println("-findNoTelefoneByIdGoogle <entityId> -- mostra o numero do entityId informado");
		System.out.println("");
		System.out.println("");
		System.out.println("-createEntity dadosEntityJson -- cria os entities no Google pelo dadosEntity.json");
		System.out.println("-createEntity listDataDontSend -- cria os entities no Google pelo arquivo de erros");
		System.out.println("");
		System.out.println("");
		System.out.println("-addEntity <noTelefone> -- cria um entity na Google pelo telefone informado");
		System.out.println("-deleteEntity <noTelefone> -- deleta a entity na Google pelo telefone informado");
		System.out.println("");
		System.out.println("");
		System.out.println("-recordCrumbs listDadosJson -- cria os crumbs apartir do arquivo dados.json");
		System.out.println("-recordCrumbs listDadosJsonDontSend -- cria os crumbs apartir do arquivo de erro no envio dos crumbs");
		System.out.println("");
		System.out.println("");
		System.out.println("==== COUNT NAO PODE SER SUPERIOR A 512 ====");
		System.out.println("-retrieveCrumbsHistory before <noTelefone> <timestamp> <count> -- Retorna a quantidade de trilha do Crumbs gravada anterior a data informada, com a quantidade informada e do numero informado");
		System.out.println("-retrieveCrumbsHistory after <noTelefone> <timestamp> <count> -- Retorna a quantidade de trilha do Crumbs gravada posterior a data informada, com a quantidade informada e do numero informado");
		System.out.println("-retrieveCrumbsHistory dayBy <noTelefone> <minTimestamp> <maxTimestamp> -- Retorna toda a trilha entre os timestamps indicado do numero informado");
		System.out.println("-retrieveCrumbsHistory <noTelefone> <timestamp> -- Retorna a trilha do timestamp ");
		System.out.println("-retrieveCrumbsHistory <noTelefone> last512 -- Retorna as ultimas 512 trilhas do numero informado" );
		System.out.println("-retrieveCrumbsHistory <noTelefone> last1 -- Retorna a ultima trilhas do numero informado" );
		System.out.println("-retrieveCrumbsHistory <noTelefone> today -- Retorna todas as trilhas do dia" );
		System.out.println("");
		System.out.println("");
		System.out.println("-deleteCrumbs <noTelefone> <minTimestamp> <maxTimestamp> -- Deleta todas as trilhas entre as datas informadas");
		System.out.println("");
		System.out.println("");
		System.out.println("==== RETORNO ====");
		System.out.println("-returnCode=01;valueRequest=987521031;valueResponse=8b9a27a64c7a4521");
		System.out.println("-returnCode=01 -- Sucesso");
		System.out.println("-returnCode=02 -- Ja Existe");
		System.out.println("-returnCode=03 -- Nao Existe");
	}

}
