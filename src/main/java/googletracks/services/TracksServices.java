package googletracks.services;

import googletracks.dao.LogDAO;
import googletracks.utils.RequiredData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Properties;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;

public class TracksServices{

    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    
    private LogDAO logDAO = new LogDAO();
    

	public String requestString(String method, String requestBody){
		try {
			/*
			 *  proxy.rio.rj.gov.br  [10.2.118.64]
			 * 
			 * http://www0.rio.rj.gov.br/proxy/cass1.pac
			 * 
			 * */
			
			String proxyHost = "10.2.118.64";  
		    String proxyPort = "8080";  
			
			System.setProperty("http.proxyHost", proxyHost);  
	        System.setProperty("http.proxyPort", proxyPort);
			
			
    		// Check for valid setup.
            Preconditions.checkArgument(!RequiredData.SERVICE_ACCOUNT_EMAIL.startsWith("mfelipesp@gmail.com"),
                "Please enter your service account e-mail from the Google APIs " +
                "Console to the SERVICE_ACCOUNT_EMAIL constant in %s",
                TracksServices.class.getName());
            
            Properties definitions = new Properties();
            File file = new File(RequiredData.PRIVATE_KEY);
            definitions.load(new FileInputStream(file));
            
            
            String p12Content = Files.readFirstLine(new File(file.getAbsolutePath()), Charset.defaultCharset());
            Preconditions.checkArgument(!p12Content.startsWith("Please"), p12Content);

            // Build service account credential.
            GoogleCredential credential =
            new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(RequiredData.SERVICE_ACCOUNT_EMAIL)
                .setServiceAccountScopes(Collections.singleton(RequiredData.TRACKS_SCOPE))
                .setServiceAccountPrivateKeyFromP12File(new File(RequiredData.PRIVATE_KEY))
                .build();
            
//            
//            String method = "entities/delete"; 
//            String requestBody = entity; //args[1];
            
            String URI = "https://www.googleapis.com/tracks/v1/" + method;
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);//http://www0.rio.rj.gov.br/proxy/cass1.pac
            
            
            
            GenericUrl url = new GenericUrl(URI);
            
            
            HttpRequest request = requestFactory.buildPostRequest(url,ByteArrayContent.fromString(null, requestBody));
            request.getHeaders().setContentType("application/json");
            // Google servers will fail to process a POST/PUT/PATCH unless the Content-Length
            // header >= 1
            //request.setAllowEmptyContent(false);
            
            
            HttpResponse shortUrl = request.execute();

            // Print response.
            BufferedReader output = new BufferedReader(new InputStreamReader(shortUrl.getContent()));
    		String resp;
    		StringBuilder sb = new StringBuilder();
    		StringBuilder test = new StringBuilder();
    		int count = 1;
    		while((resp = output.readLine()) != null){
    			
    			sb.append(resp);
    			sb.append("\n");
    			
    			test.append(resp);
    			test.append(count);
    			test.append("\n");
    			count ++;
    		}
    		//System.out.println(test.toString());
    		
    		return sb.toString();
    		
    		
    		
		} catch (Exception e) {
			
			String errors = e.getMessage();
			String[] error = errors.split("\n");
//			System.out.println("ERRO na transacao com o Google");
			
//			System.out.println(e.getMessage()); // Completao
			System.out.println(error[0]); // motivo
			System.out.println(error[7]); // msg
//			System.out.println(error[6]); // reason
//			System.out.println(error[5]); // domain
			System.out.println(error[10]); //code
			
			String montagem = "";
			
			if(e.getMessage().trim().equalsIgnoreCase("connect timed out")){
				montagem = "ERRO DE CONEXAO";
			} else {
				String erroTotal =  error[5] + error[6] + error[10]+ error[7];
				montagem = "{" + erroTotal +"}";
			}
			
			
			logDAO.createERROR("Erro na Transacao com a google");
			logDAO.createERROR(montagem);
			
			
			return montagem;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
