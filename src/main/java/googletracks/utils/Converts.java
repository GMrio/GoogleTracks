package googletracks.utils;

import java.util.Date;

public class Converts {
	
	public String timestampGoogleForTimestamp(String timestampGoogle){
		
		String timestamp = timestampGoogle;
		
		timestamp = timestamp.replace(".", "");
		
		int x = timestamp.indexOf('E');
		
		timestamp = timestamp.substring(0, x);
		
		return timestamp;
	}
	
	
	public Date timestampForDate(Long timestamp){
		
		Date date = new Date();
		date.setTime(timestamp);
		
		return date;
	}
	
	public Long dateForTimestamp ( Date data ){
		return data.getTime();
	}
	
	
}
