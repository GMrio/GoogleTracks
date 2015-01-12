package googletracks.model.report;

public class Address {
	
	private String name;
	private String locality;
	private String state;
	private String country;
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", locality=" + locality + ", state="
				+ state + ", country=" + country + "]";
	}
	
	
	
}
