package googletracks.entities;

public class Error {
	
	private Integer code;
	private String reason;
	private String domain;
	private String message;
	
	public Error() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", reason=" + reason + ", domain="
				+ domain + ", message=" + message + "]";
	}
	
	

}
