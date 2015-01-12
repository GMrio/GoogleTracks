package googletracks.tdd;

import java.io.File;

public class MainzinhaTestCrazy {
	public static void main(String[] args) {
		
		boolean stopWhile = true;
		int count = 1;
		while (stopWhile) {
			
			File file = new File("C:/Users/u6448938/Googletracks/M1_"+count+".zip");
			long lenght = file.length();
			
			System.out.println("File info ::: " + file.getAbsolutePath() + " tamanho do arquivo : " + file.length());
			
			if(!file.exists()){
				System.out.println("cria o proximo  M1_" + count +".zip");
				System.out.println("Escreve nele");
			}
			
			if(lenght > 1000000 ){
				System.out.println("Pula para o proximo");
				count ++;
			}else {
				System.out.println("Escreve");
				stopWhile = false;
				break;
			}
			
		}
		
		
	  
 
		
	}
	
}
