package googletracks.exception;

public class Errors {
	private String domain;
	private String reason;
	private String message;
	public Errors() {
		// TODO Auto-generated constructor stub
	}
	public Errors(String domain, String reason, String message) {
		super();
		this.domain = domain;
		this.reason = reason;
		this.message = message;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Errors [domain=" + domain + ", reason=" + reason + ", message="
				+ message + "]";
	}
	
	
}
