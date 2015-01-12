package googletracks.model;

public class DatabaseFile {

	private String id;
	private String name;
	
	public DatabaseFile() {
		// TODO Auto-generated constructor stub
	}

	public DatabaseFile(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EntityNoTelefone [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
}
