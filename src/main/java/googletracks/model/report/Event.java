package googletracks.model.report;

public class Event {
	private String type;
	private String detail;
	private String timestamp;
	public Event() {
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Address [type=" + type + ", detail=" + detail + ", timestamp="
				+ timestamp + "]";
	}
}
