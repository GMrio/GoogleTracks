package googletracks.run;

import googletracks.entities.EntitysRetrieve;
import googletracks.services.TracksServices;
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
import com.google.gson.Gson;

public class ListEntity {
	
    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public static void main(String[] args) throws Exception {
		
		// Check for valid setup.
        Preconditions.checkArgument(!RequiredData.SERVICE_ACCOUNT_EMAIL.startsWith("[["),
            "Please enter your service account e-mail from the Google APIs " +
            "Console to the SERVICE_ACCOUNT_EMAIL constant in %s",
            TracksServices.class.getName());
        
        
        Properties definitions = new Properties();
        File file = new File(RequiredData.PRIVATE_KEY);
        definitions.load(new FileInputStream(file));
        
        System.out.println("Arquivo chave : " + file.exists());
        System.out.println("ARQUIVO === " + file.getAbsolutePath());
        
        
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
        
        
        String method = "entities/list"; 
        String requestBody = ""; //args[1];
        
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
        for (String line = output.readLine(); line != null; line = output.readLine()) {
            System.out.println(line);
        }
		
		
		String resp;
		StringBuilder sb = new StringBuilder();
		while((resp = output.readLine()) != null){
			sb.append(resp);
			sb.append("\n");
		}
		
		
		
		Gson gson = new Gson();
		EntitysRetrieve retrieve = new EntitysRetrieve();
		retrieve = gson.fromJson(output, EntitysRetrieve.class);
		
		for(int i=0 ; i<retrieve.getEntities().size(); i++){
			System.out.println(retrieve.getEntities().get(i).getName());
		}
	}

}
