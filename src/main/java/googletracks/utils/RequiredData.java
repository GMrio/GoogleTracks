package googletracks.utils;

public class RequiredData {
	
	

	
	/** E-mail address of the service account. = MFELIPESP@GMAIL.COM*/
	 public static final String SERVICE_ACCOUNT_EMAIL=
		        "130983055749-lb1slhitciolg4pua75shdn9kkrnus13@developer.gserviceaccount.com";
    
    
    
    /** Global configuration of OAuth 2.0 scope. */
    public static final String TRACKS_SCOPE="https://www.googleapis.com/auth/tracks";

    /** Global configuration for location of private key file. === MFELIPESP@GMAIL.COM*/
    public static final String PRIVATE_KEY= "C:/Googletracks/Google Maps APIs for Business - Asset Tracking - GUARDA MUNICIP-156e5c40ac68.p12";
    
    
    /** Arquivo para montar os crumbs  */
	public final static String FILE_ENTITIES = "C:/Googletracks/entities.json";
	
	
	public final static String ARQUIVO_DADOS_ENTITY_JSON = "C:/Googletracks/dadosEntity.json";
	
	/** Arquivos para associar o EntityIDs com o numero de celular */
	public final static String FILE_DADOS_JSON = "C:/Googletracks/dados.json";
	
	/** Arquivos de erro*/
	public final static String FILE_LOG_ERROR= "C:/Googletracks/log/error/";
	
	/** Arquivos de info*/
	public final static String FILE_LOG_INFO= "C:/Googletracks/log/info/";
	
	/** Arquivos de JSON que não foram inserido no Google*/
	public final static String FILE_DADOS_JSON_NOT_SEND= "C:/Googletracks/dados_json_nao_cadastro.json";
	
	/** Arquivos de JSON que não foram gravados no crumbs*/
	public final static String FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB= "C:/Googletracks/dados_json_nao_cadastro_crumbs.json";
	
	

}
