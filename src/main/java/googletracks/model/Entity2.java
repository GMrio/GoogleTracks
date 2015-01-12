package googletracks.model;

public class Entity2 {
	private String id;
	private String name;
	private TypeEntity type;
	public Entity2() {
		// TODO Auto-generated constructor stub
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
	public TypeEntity getType() {
		return type;
	}
	public void setType(TypeEntity type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Entity2 [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
}
