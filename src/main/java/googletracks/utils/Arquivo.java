package googletracks.utils;

import java.io.File;
import java.io.RandomAccessFile;


public class Arquivo  {

	/*
	 * UMA DAS Classes responsaveis pela manipula��o de arquivo
	 */
	RandomAccessFile raf;
	String caminho;
	
	public Arquivo() {
		
	}
	public void abrirArquivo(String caminho) throws Exception {
		this.caminho = caminho;
		raf = new RandomAccessFile(caminho, "rw");
	}

	
	public void escreverNoInicio(String texto) throws Exception {
		/*
		 * raf.seek -> Posicionar o cursor
		 * raf.length() -> Pegar o tamanho do arquivo
		 */
		removerArquivos();
		abrirArquivo(caminho);
		raf.seek(0);
		/*
		 * Escrever no arquivo
		 */
		raf.writeBytes(texto);
	}
	
	public void escreverNoFinal(String texto) throws Exception {
		/*
		 * raf.seek -> Posicionar o cursor
		 * raf.length() -> Pegar o tamanho do arquivo
		 */
		raf.seek(raf.length());
		/*
		 * Escrever no arquivo
		 */
		raf.writeBytes(texto);
	}

	
	public void fechar() throws Exception {
		raf.close();
	}

	
	public String lerArquivo() throws Exception {
		String resp;
		/*
		 * Classe utilizada para manipular String
		 */
		StringBuilder sb = new StringBuilder();
		while((resp = raf.readLine()) != null){
			sb.append(resp);
			sb.append("\n");
		}
		
		if(sb.toString() != null){
			return sb.toString();
		} else {
			return null;
		}
		
		
	}
	public void removerArquivos() {
		File f = new File(caminho);
		f.delete();
		
	}

	
	
}
