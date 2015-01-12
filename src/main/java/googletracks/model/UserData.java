package googletracks.model;

public class UserData {
	

	private String idEstado;
	private String bateria;
	private String gps;
	private String imei;
	private String logado;
	
	public UserData() {
		// TODO Auto-generated constructor stub
	}

	public UserData(String idEstado, String bateria, String gps, String imei, String logado) {
		super();
		this.idEstado = idEstado;
		this.bateria = bateria;
		this.gps = gps;
		this.imei = imei;
		this.logado = logado;
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

	public String getLogado() {
		return logado;
	}

	public void setLogado(String logado) {
		this.logado = logado;
	}

	@Override
	public String toString() {
		return "UserData [idEstado=" + idEstado + ", bateria=" + bateria
				+ ", gps=" + gps + ", imei=" + imei + ", logado=" + logado
				+ "]";
	}

	
	
	

}