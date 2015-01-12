package googletracks.model;


public class DadosJson {

	private String noTelefone;
	private String MtUsuario;
	private String latitude;
	private String longitude;
	private String data;
	private String idEstado;
	private String bateria;
	private String sinal;
	private String frequencia;
	private String gps;
	private String precisao;
	private String imei;
	private String SIMCard;
	private String logado;
	private String versaoaplicacao;
	private String orientacao;
	private String versaoandroid;
	private String rede;
	private String kiosk;

	public DadosJson() {
		// TODO Auto-generated constructor stub
	}



	public DadosJson(String noTelefone, String mtUsuario, String latitude,
			String longitude, String data, String idEstado, String bateria,
			String sinal, String frequencia, String gps, String precisao,
			String imei, String sIMCard, String logado, String versaoaplicacao,
			String orientacao, String versaoandroid, String rede, String kiosk) {
		super();
		this.noTelefone = noTelefone;
		MtUsuario = mtUsuario;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
		this.idEstado = idEstado;
		this.bateria = bateria;
		this.sinal = sinal;
		this.frequencia = frequencia;
		this.gps = gps;
		this.precisao = precisao;
		this.imei = imei;
		SIMCard = sIMCard;
		this.logado = logado;
		this.versaoaplicacao = versaoaplicacao;
		this.orientacao = orientacao;
		this.versaoandroid = versaoandroid;
		this.rede = rede;
		this.kiosk = kiosk;
	}



	public String getPrecisao() {
		return precisao;
	}





	public void setPrecisao(String precisao) {
		this.precisao = precisao;
	}





	public String getNoTelefone() {
		return noTelefone;
	}

	public void setNoTelefone(String noTelefone) {
		this.noTelefone = noTelefone;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getBateria() {
		return bateria;
	}

	public void setBateria(String bateria) {
		this.bateria = bateria;
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSIMCard() {
		return SIMCard;
	}

	public void setSIMCard(String sIMCard) {
		SIMCard = sIMCard;
	}

	public String getLogado() {
		return logado;
	}

	public void setLogado(String logado) {
		this.logado = logado;
	}

	public String getVersaoaplicacao() {
		return versaoaplicacao;
	}

	public void setVersaoaplicacao(String versaoaplicacao) {
		this.versaoaplicacao = versaoaplicacao;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public String getVersaoandroid() {
		return versaoandroid;
	}

	public void setVersaoandroid(String versaoandroid) {
		this.versaoandroid = versaoandroid;
	}

	public String getRede() {
		return rede;
	}

	public void setRede(String rede) {
		this.rede = rede;
	}

	public String getKiosk() {
		return kiosk;
	}

	public void setKiosk(String kiosk) {
		this.kiosk = kiosk;
	}

	public String getMtUsuario() {
		return MtUsuario;
	}

	public void setMtUsuario(String mtUsuario) {
		MtUsuario = mtUsuario;
	}

	@Override
	public String toString() {
		return "DadosJson [noTelefone=" + noTelefone + ", MtUsuario="
				+ MtUsuario + ", latitude=" + latitude + ", longitude="
				+ longitude + ", data=" + data + ", idEstado=" + idEstado
				+ ", bateria=" + bateria + ", sinal=" + sinal + ", frequencia="
				+ frequencia + ", gps=" + gps + ", precisao=" + precisao
				+ ", imei=" + imei + ", SIMCard=" + SIMCard + ", logado="
				+ logado + ", versaoaplicacao=" + versaoaplicacao
				+ ", orientacao=" + orientacao + ", versaoandroid="
				+ versaoandroid + ", rede=" + rede + ", kiosk=" + kiosk + "]";
	}
	
	
	
	
	
	
}
