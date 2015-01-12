package googletracks.entities;

import googletracks.model.DatabaseFile;

import java.util.ArrayList;
import java.util.List;


public class DatabaseFileManager {
	
	private List<DatabaseFile> entities = new ArrayList<DatabaseFile>();
	
	public DatabaseFileManager() {
		// TODO Auto-generated constructor stub
	}

	public List<DatabaseFile> getEntities() {
		return entities;
	}

	public void setEntities(List<DatabaseFile> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "EntityNoTelelefonesVO [entities=" + entities + "]";
	}
	
	
}
