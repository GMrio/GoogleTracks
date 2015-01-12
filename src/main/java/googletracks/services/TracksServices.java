package googletracks.services;

import googletracks.dao.LogDAO;
import googletracks.utils.RequiredData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Properties;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.compute.ComputeCredential;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.PlusScopes;
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
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
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
//			System.out.println(error[0]); // motivo
//			System.out.println(error[7]); // msg
//			System.out.println(error[6]); // reason
//			System.out.println(error[5]); // domain
//			System.out.println(error[10]); //code
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static Credential authorize() throws Exception {
		// load client secrets
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, 
				new FileReader("C:/Users/u6448938/Desktop/GoogleTracks/client_secret_130983055749-bm1nv2hphddqsna6cvvikgaovtska3lb.apps.googleusercontent.com.json"));
		if (clientSecrets.getDetails().getClientId().startsWith("Enter")
				|| clientSecrets.getDetails().getClientSecret()
						.startsWith("Enter ")) {
			System.out
					.println("Enter Client ID and Secret from https://code.google.com/apis/console/?api=plus "
							+ "into plus-cmdline-sample/src/main/resources/client_secrets.json");
			System.exit(1);
		} else {
			System.out.println("ERROR");
		}
		// set up authorization code flow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
				Collections.singleton(PlusScopes.PLUS_ME)).build();
		// authorize
		return new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");
	}
	
	
	
	
	
	
	public String requestAuthJson(String method, String requestBody){
		try {
    		// Check for valid setup.
            Preconditions.checkArgument(!RequiredData.SERVICE_ACCOUNT_EMAIL.startsWith("mfelipesp@gmail.com"),
                "Please enter your service account e-mail from the Google APIs " +
                "Console to the SERVICE_ACCOUNT_EMAIL constant in %s",
                TracksServices.class.getName());
            
        
            
           
            
            
           
            
            
            
            ComputeCredential credential = (ComputeCredential) authorize();
            
            
            
      
            String URI = "https://www.googleapis.com/tracks/v1/" + method;
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
            GenericUrl url = new GenericUrl(URI);
            
            HttpRequest request = requestFactory.buildPostRequest(url,ByteArrayContent.fromString(null, requestBody));
            request.getHeaders().setContentType("application/json");
     
            
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
    		
    		
    		return sb.toString();
    		
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return requestBody;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
