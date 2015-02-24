package googletracks.dao;

import googletracks.utils.Arquivo;
import googletracks.utils.RequiredData;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogDAO {
	
	public String today(){
		
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy");
		String date = df.format(new Date());
		return date;
	}
	
	public void createFiles(){
		
		
		File file = new File(RequiredData.FILE_LOG_INFO);
		if(!file.exists()){
			file.mkdirs();
		}
		
		file = new File(RequiredData.FILE_LOG_ERROR);
		if(!file.exists()){
			file.mkdirs();
		}
		
	}
	
	public void createINFO(String info) throws Exception{
		createFiles();
		try {
			
			boolean stopWhile = true;
			int count = 1;
			while (stopWhile) {
				
				String today = "info_"+ today() + "_" + count + ".log";
				File file = new File(RequiredData.FILE_LOG_INFO + today);
				long lenght = file.length();
				
				//System.out.println("File info ::: " + file.getAbsolutePath() + " tamanho do arquivo : " + file.length());
				
				
				Date date = new Date();
				String msg =  "INFO :" + date + " - " + info + " \n";
				
				if(!file.exists()){
					FileWriter fileWriter = new  FileWriter(file);
					fileWriter.write(info);
					fileWriter.close();
				}
				
				if(lenght > 1000000 ){ // 1 000 000
					//System.out.println("Pula para o proximo");
					count ++;
				}else {
					Arquivo arq = new Arquivo();
					arq.abrirArquivo(file.getAbsolutePath());
					arq.escreverNoFinal(msg);
					arq.fechar();
					//System.out.println("Escreve");
					stopWhile = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void createERROR(String error) {
		createFiles();
		try {
			
			boolean stopWhile = true;
			int count = 1;
			while (stopWhile) {
				
				String today = "error_"+ today() + "_" + count + ".log";
				File file = new File(RequiredData.FILE_LOG_ERROR + today);
				long lenght = file.length();
				
			
						
				Date date = new Date();
				String msg =  "INFO :" + date + " - " + error + " \n";
				
				if(!file.exists()){
					FileWriter fileWriter = new  FileWriter(file);
					fileWriter.write(error);
					fileWriter.close();
				}
				
				if(lenght > 1000000 ){// 1 .000 .000 bytes
					count ++;
				}else {
					Arquivo arq = new Arquivo();
					arq.abrirArquivo(file.getAbsolutePath());
					arq.escreverNoFinal(msg);
					arq.fechar();
					//System.out.println("Escreve");
					stopWhile = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
