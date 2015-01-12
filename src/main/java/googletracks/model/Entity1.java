package googletracks.model;

public class Entity1 {

	private String name;
	private TypeEntity type;
	
	public Entity1() {
		// TODO Auto-generated constructor stub
	}

	public Entity1(String name, TypeEntity type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeEntity getType() {
		return type;
	}

	public void setType(TypeEntity type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Entity1 [name=" + name + ", type=" + type + "]";
	}
	
	
	
}
