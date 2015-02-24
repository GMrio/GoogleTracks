package googletracks.regras;

import googletracks.dao.LogDAO;

public class NoTelefoneRegras {
	
	
	
	public boolean noTelefoneCorreto(String noTelefone){
		LogDAO logDAO = new LogDAO();
		
		try {
			Integer testeNoTelefone = Integer.parseInt(noTelefone);
			testeNoTelefone = 0;
		
			
			return true;
		} catch (Exception e) {
			System.out.println("nao é numero de telefone");
			try {
				logDAO.createINFO("Não foi adicionado um numero correto");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}

}
