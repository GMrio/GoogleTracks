package googletracks.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class CalendariosDatas {
	
	private static final DateFormat FORMATADOR = new SimpleDateFormat("dd/MM/yyyy");
	
	private Calendar getDataAtual() {
		Calendar dataAtual = new GregorianCalendar();
		// limpamos informações de hora, minuto, segundo
		// e milissegundos
		dataAtual.set(Calendar.HOUR_OF_DAY, 0);
		dataAtual.set(Calendar.MINUTE, 0);
		dataAtual.set(Calendar.SECOND, 0);
		dataAtual.set(Calendar.MILLISECOND, 0);
		return dataAtual;
	}
	
	public Date getRetornaDataHojeOuOntem(String value){
		
		Date dataConvertida = null;
		
		if (value == null || value.equals("")) {
			return null;
		}
		
		if ("hoje".equalsIgnoreCase(value)) {
			dataConvertida = getDataAtual().getTime();
			
		} else if ("ontem".equalsIgnoreCase(value)) {
			
			Calendar ontem = getDataAtual();
			ontem.add(Calendar.DAY_OF_MONTH, -1);
			dataConvertida = ontem.getTime();
			
		} else {
					
		}
		
		return dataConvertida;
	}
	
	

}
