package googletracks.tdd;

import googletracks.controller.CrumbsController;
import googletracks.regras.NoTelefoneRegras;
import googletracks.utils.CalendariosDatas;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TesteUnitariosJUnits {

	@Test
	public void hojeEOntem(){
		
		CalendariosDatas datas = new CalendariosDatas();
		
		Date ontem = datas.getRetornaDataHojeOuOntem("ontem");
		Date hoje = datas.getRetornaDataHojeOuOntem("hoje");
		
		System.out.println("Ontem : " + ontem);
		System.out.println("Hoje : " + hoje);
		
		System.out.println("Timemillis");
		System.out.println("Ontem : " + (ontem.getTime()/1000L));
		System.out.println("Hoje : " + (hoje.getTime()/1000L));
		
		Assert.assertEquals(1, 1);
	}
	
	
	@Test
	public void retornaTodoCrumbsHoje(String noTelefone) {
		NoTelefoneRegras noTelefoneRegras = new NoTelefoneRegras();
		if(noTelefoneRegras.noTelefoneCorreto(noTelefone)){
			
			//APENAS EM TESTE
			CrumbsController controller = new CrumbsController();
			
			try {
				
				CalendariosDatas datas = new CalendariosDatas();
				
				Long timeOntem = datas.getRetornaDataHojeOuOntem("ontem").getTime() /1000L ;
				Long timeHoje= datas.getRetornaDataHojeOuOntem("hoje").getTime() /1000L;
				
				
				//logDAO.createINFO("Informação de todos os crumbs de hoje");
				
				controller.retrieveCrumbsHistoryDayById2(noTelefone, timeOntem.toString(), timeHoje.toString());
				
				
				
			} catch (Exception e) {
				
			}
			
			Assert.assertEquals(1, 1);
		}
		
	}
	
}
