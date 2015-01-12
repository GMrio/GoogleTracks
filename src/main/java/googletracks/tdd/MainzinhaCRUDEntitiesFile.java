package googletracks.tdd;

import googletracks.dao.DatabaseFileDAO;
import googletracks.entities.DatabaseFileManager;
import googletracks.model.DatabaseFile;

public class MainzinhaCRUDEntitiesFile {
	public static void main(String[] args) {
		
		DatabaseFileDAO databaseFileDAO = new DatabaseFileDAO();
		
		/**
		 * Salvar 1 entity
		 */

//		DatabaseFile databaseFile = new DatabaseFile("19da4s5", "2481569");
//		databaseFileDAO.saveEntities(databaseFile);
		
		/**
		 * Remover 1 entity
		 */
		
//		DatabaseFile databaseFile = new DatabaseFile("19da4s5","984769000");
//		databaseFileDAO.removeDatabaseFile(databaseFile);
		
		/**
		 * 
		 * Listando Todos
		 */
		
//		DatabaseFileManager databaseFileManager = databaseFileDAO.findAllDatabaseFile();
//		System.out.println(databaseFileManager.getEntities());
		
		/**
		 * Buscando por numero 
		 */
		
//		String id = databaseFileDAO.findByIdGoogleFromNoTelefone("984769071");
//		System.out.println(id);
	}

}
