package googletracks.dao;

import googletracks.entities.DadosJsonVO;
import googletracks.model.DadosJson;
import googletracks.utils.RequiredData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

import com.google.gson.Gson;

/**
 * 
 * @author marcos
 * Essa classe faz a gerência dos arquivos que não foram enviado para o Google.
 * 
 * dadosJsonNotSend.txt e dados.json
 * 
 */

public class DadosJsonDAO {
	
	private LogDAO logDAO = new LogDAO();
	
	/**
	 *  Lendo arquivo dados.json
	 */
	public DadosJsonVO findAllDadosJson() {
		Gson gson = new Gson();
		DadosJsonVO crumbs = new DadosJsonVO();
		try {
			BufferedReader output = new BufferedReader(new FileReader(RequiredData.FILE_DADOS_JSON));
			crumbs = gson.fromJson(output, DadosJsonVO.class);
			return crumbs;
		} catch (Exception e) {
			String error = e.getMessage();
			logDAO.createERROR("Erro no DadosJsonDAO.findAllDadosJson()");
			logDAO.createERROR(error);
			return null;
		}
		
	}
	
	/**
	 *  Lendo arquivo dados_json_nao_cadastro_crumbs.json
	 */
	public DadosJsonVO findAllDadosJsonDontSendCrumbs() {
		Gson gson = new Gson();
		DadosJsonVO crumbs = new DadosJsonVO();
		try {
			BufferedReader output = new BufferedReader(new FileReader(RequiredData.FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB));
			crumbs = gson.fromJson(output, DadosJsonVO.class);
			return crumbs;
		} catch (Exception e) {
			String error = e.getMessage();
			logDAO.createERROR("Erro no Find File Json");
			logDAO.createERROR(error);
			return null;
		}
		
	}
	
	
	
	/** 
	 * Criando arquivo onde não foi cadastrado os dados na Google (dadosJsonNotSend.txt).
	 */
		
	public void createFileDadosJsonNotSend(){
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND);
			Gson gson = new Gson();

			if (!file.exists()) {
				RandomAccessFile raf = new RandomAccessFile(RequiredData.FILE_DADOS_JSON_NOT_SEND, "rw");
				raf.seek(raf.length());

				DadosJson doc = new DadosJson("null", "null", "null", "null",
						"null", "null", "null", "null", "null", "null","null", "null",
						"null", "null", "null", "null", "null", "null", "null");

				DadosJsonVO docsVO = new DadosJsonVO();

				docsVO.getCrumbs().add(doc);
				
				String json = gson.toJson(docsVO); 

				raf.writeBytes(json);
				raf.close();
			}
		} catch (Exception e) {
			logDAO.createERROR("Erro no createFileDadosJsonNotSend");
			logDAO.createERROR(e.getMessage());
		}
		
	}
	
	/** 
	 * Criando arquivo onde não foi cadastrado os dados na Google (dadosJsonNotSend.txt).
	 */
		
	public void createFileDadosJsonNotSendForCrumbs(){
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB);
			Gson gson = new Gson();

			if (!file.exists()) {
				RandomAccessFile raf = new RandomAccessFile(RequiredData.FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB, "rw");
				raf.seek(raf.length());

				DadosJson doc = new DadosJson("null", "null", "null", "null",
						"null", "null", "null", "null", "null", "null","null", "null",
						"null", "null", "null", "null", "null", "null", "null");

				DadosJsonVO docsVO = new DadosJsonVO();

				docsVO.getCrumbs().add(doc);
				
				String json = gson.toJson(docsVO); 

				raf.writeBytes(json);
				raf.close();
			}
		} catch (Exception e) {
			logDAO.createERROR("Erro no createFileDadosJsonNotSendForCrumbs");
			logDAO.createERROR(e.getMessage());
		}
		
	}
	
	
	
	/**
	 * 
	 * Lendo arquivo que deram erro no envio
	 */
	
	public DadosJsonVO findAllDadosJsonNotSend() {
		Gson gson = new Gson();
		DadosJsonVO crumbs = new DadosJsonVO();
		try {
			BufferedReader output = new BufferedReader(new FileReader(RequiredData.FILE_DADOS_JSON_NOT_SEND));
			crumbs = gson.fromJson(output, DadosJsonVO.class);
		} catch (Exception e) {
			System.out.println("Erro no findAllFileDadosJsonNotSend");
			logDAO.createERROR("Erro no findAllFileDadosJsonNotSend");
			logDAO.createERROR(e.getMessage());
		}
		return crumbs;
	}
	
	/**
	 * 
	 * Cadastrando no arquivo dadosJsonNotSend.txt
	 */
	
	public void saveDadosJsonNotSend(DadosJson doc) {
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND);
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			Gson gson = new Gson();
		
			DadosJsonVO documentsJonsVO = gson.fromJson(br, DadosJsonVO.class);
			documentsJonsVO.getCrumbs().add(doc);

			String json = gson.toJson(documentsJonsVO);
			
			file.delete();
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			br.close();

		} catch (Exception e) {
			logDAO.createERROR("Erro no DadosJsonDAO.saveDadosJsonNotSend");
			logDAO.createERROR(e.getMessage());
		}

	}
	
	
	/**
	 * 
	 * Cadastrando no arquivo dadosJsonNotSendForCrumbs.txt
	 */
	
	public void saveDadosJsonNotSendForCrumbs(DadosJson doc) {
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB);
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			Gson gson = new Gson();
		
			DadosJsonVO documentsJonsVO = gson.fromJson(br, DadosJsonVO.class);
			documentsJonsVO.getCrumbs().add(doc);

			String json = gson.toJson(documentsJonsVO);
			
			file.delete();
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			br.close();

		} catch (Exception e) {
			logDAO.createERROR("Erro no DadosJsonDAO.saveDadosJsonNotSend");
			logDAO.createERROR(e.getMessage());
		}

	}
	
	
	
	/**
	 * 
	 * Remove do arquivo dadosJsonNotSend.txt
	 */
	
	public void removeDadosJsonNotCreate(DadosJson dadosJson) {
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			Gson gson = new Gson();

			
			DadosJsonVO dadosJonsVO = new DadosJsonVO();
			dadosJonsVO = gson.fromJson(br, DadosJsonVO.class);
			
			
			int sizeList = dadosJonsVO.getCrumbs().size();
			
	//		System.out.println("Tamanho da Lista " + sizeList);
			
			for(int i = 0; i < sizeList ;  i ++){
				
				if(dadosJson.getNoTelefone().equals(
						dadosJonsVO.getCrumbs().get(i).getNoTelefone())){
					
					dadosJonsVO.getCrumbs().remove(i);
					
		//			System.out.println("REMOVENDO DA LISTA DE NAO ENVIADOS");
					break;
				}
			}

			String json = gson.toJson(dadosJonsVO);
			
		//	System.out.println("TAMANHO :");
		//	System.out.println(dadosJonsVO.getDocument().size());

			file.delete();
			
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * Remove do arquivo dadosJsonNotSendCrumbs.txt
	 */
	
	public void removeDadosJsonNotSendCrumbs(DadosJson dadosJson) {
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON_NOT_SEND_FOR_CRUMB);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			Gson gson = new Gson();

			
			DadosJsonVO dadosJonsVO = new DadosJsonVO();
			dadosJonsVO = gson.fromJson(br, DadosJsonVO.class);
			
			
			int sizeList = dadosJonsVO.getCrumbs().size();
			
			//System.out.println("Tamanho da Lista " + sizeList);
			
			for(int i = 0; i < sizeList ;  i ++){
				
				if(dadosJson.getNoTelefone().equals(
						dadosJonsVO.getCrumbs().get(i).getNoTelefone())){
					
					dadosJonsVO.getCrumbs().remove(i);
					
					//System.out.println("REMOVENDO DA LISTA DE NAO ENVIADOS");
					break;
				}
			}

			String json = gson.toJson(dadosJonsVO);
			
			//System.out.println("TAMANHO :");
			//System.out.println(dadosJonsVO.getDocument().size());

			file.delete();
			
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void removeDadosJson(DadosJson dadosJson) {
		try {
			File file = new File(RequiredData.FILE_DADOS_JSON);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			Gson gson = new Gson();

			
			DadosJsonVO dadosJonsVO = new DadosJsonVO();
			dadosJonsVO = gson.fromJson(br, DadosJsonVO.class);
			
			
			int sizeList = dadosJonsVO.getCrumbs().size();
			
			//System.out.println("Tamanho da Lista " + sizeList);
			
			for(int i = 0; i < sizeList ;  i ++){
				
				if(dadosJson.getNoTelefone().equals(
						dadosJonsVO.getCrumbs().get(i).getNoTelefone())){
					
					dadosJonsVO.getCrumbs().remove(i);
					
					//System.out.println("REMOVENDO DA LISTA DE NAO ENVIADOS");
					break;
				}
			}

			String json = gson.toJson(dadosJonsVO);
			
			//System.out.println("TAMANHO :");
			//System.out.println(dadosJonsVO.getDocument().size());

			file.delete();
			
			FileWriter write = new FileWriter(file);
			write.write(json);
			write.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
