package googletracks.tdd;

import googletracks.entities.CrumbsDelete;
import googletracks.entities.Error;
import googletracks.services.TracksServices;
import googletracks.utils.Converts;

import com.google.gson.Gson;

public class CriandoJSONCrumbDelete {
	public static void main(String[] args) {
		
		
		Converts conversor = new Converts();
		String novo = conversor.timestampGoogleForTimestamp("1.417346028E9");
		
		Long maxTime = Long.parseLong(novo);
		
		CrumbsDelete delte = new CrumbsDelete("e35122aba6718694X",  1417775515L, maxTime);
		Gson g = new Gson();
		String json = g.toJson(delte);
		
		System.out.println(json);
		
		
		System.out.println("Transação");
		TracksServices service = new TracksServices();
		String response = service.requestString("crumbs/delete", json);
		
		
		
		System.out.println("Resposta");
		if(response.trim().equals("{}")){
			System.out.println("OK -- Deletado com Sucesso");
		} else {
			Error error = g.fromJson(response, Error.class);
			System.out.println(error.getCode());
			System.out.println(error.getMessage());
		}
		
	}

}
