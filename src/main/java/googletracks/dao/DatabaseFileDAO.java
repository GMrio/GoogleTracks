package googletracks.dao;

import googletracks.entities.DatabaseFileManager;
import googletracks.model.DatabaseFile;
import googletracks.utils.RequiredData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

import com.google.gson.Gson;

public class DatabaseFileDAO{

	private Gson gson = new Gson();
	private LogDAO logDAO = new LogDAO();

	/**
	 * 
	 * Criando a database file
	 */
	public void createFileEntities() throws Exception {
		File file = new File(RequiredData.FILE_ENTITIES);
		Gson gson = new Gson();

		if (!file.exists()) {
			RandomAccessFile raf = new RandomAccessFile(
					RequiredData.FILE_ENTITIES, "rw");
			raf.seek(raf.length());

			DatabaseFile databaseFile = new DatabaseFile("null", "null");
			DatabaseFileManager databaseFileManager = new DatabaseFileManager();
			databaseFileManager.getEntities().add(databaseFile);

			String json = gson.toJson(databaseFileManager);
			
			//System.out.println(json); -- saida de json
			
			
			raf.writeBytes(json);
			raf.close();
		}
	}

	
	
	/**
	 * 
	 * Busca pelo idGoogle do noTelefone
	 */
	public String findByIdGoogleFromNoTelefone(String noTelefone){
		try {
			DatabaseFileManager databaseFileManager = new DatabaseFileManager();
			
			BufferedReader br = new BufferedReader(new FileReader(new File(RequiredData.FILE_ENTITIES)));
			
			// Coloca todos para Uso
			databaseFileManager = gson.fromJson(br, DatabaseFileManager.class);
			br.close();
			//Procurando o noTelefone no arquivo 
			for (DatabaseFile dbf : databaseFileManager.getEntities()) {
				
				if (noTelefone.equals(dbf.getName())) {
					return dbf.getId();
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("ERRO NO DatabaseFileDao.findByIdGoogleFromNoTelefone()");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}


	
	
	
	/**
	 * 
	 * Criando um Entity do DatabaseFile
	 */
	public boolean saveEntities(DatabaseFile databaseFile){
		try {
			BufferedReader bf = new BufferedReader(new FileReader(new File(RequiredData.FILE_ENTITIES)));
			
			//adicionar na google a entity e coletar o id
			Gson gson = new Gson();
			DatabaseFileManager databaseFileManager = new DatabaseFileManager();
			databaseFileManager = gson.fromJson(bf, DatabaseFileManager.class);
			
			databaseFileManager.getEntities().add(databaseFile);
			
			//pega a lista toda transforma em json 
			String json = gson.toJson(databaseFileManager);
			
			//salva esse json gerado em no documento.
			FileWriter write = new FileWriter(new File(RequiredData.FILE_ENTITIES));
			write.write(json);
			write.close();
			return true;
		} catch (Exception e) {
			logDAO.createERROR("Erro no DatabaseFileDao.saveEntities");
			return false;
		}
	}
	
	/**
	 * 
	 * Deletando do DatabaseFile
	 */
	public boolean removeDatabaseFile(DatabaseFile databaseFile) {
		try {
			
			BufferedReader bf = new BufferedReader(new FileReader(new File(RequiredData.FILE_ENTITIES)));
			Gson gson = new Gson();
			DatabaseFileManager databaseFileManager = gson.fromJson(bf, DatabaseFileManager.class);
			bf.close();
			
			
			for(int i = 1; i < databaseFileManager.getEntities().size(); i ++){
				
				if(databaseFile.getName().equals(databaseFileManager.getEntities().get(i).getName())){
					logDAO.createINFO("REMOVENDO O NUMERO == " + databaseFileManager.getEntities().get(i).getName());
					databaseFileManager.getEntities().remove(i);
					break;
				}
				
			}
			
			String json = gson.toJson(databaseFileManager);
			
			File file = new File(RequiredData.FILE_ENTITIES);
			file.delete();
			
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao excluir uma entity na database file");
			return false;
		}

	}
	
	/**
	 * 
	 * Listando todos os Entities do DatabaseFile
	 */
	public DatabaseFileManager findAllDatabaseFile() {
		DatabaseFileManager database = new DatabaseFileManager();
		try {
			BufferedReader output = new BufferedReader(new FileReader(RequiredData.FILE_ENTITIES));
			database = gson.fromJson(output, DatabaseFileManager.class);
			return database;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
