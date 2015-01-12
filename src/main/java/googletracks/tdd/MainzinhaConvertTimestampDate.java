package googletracks.tdd;

import java.sql.Timestamp;
import java.util.Date;


public class MainzinhaConvertTimestampDate {
	public static void main(String[] args) throws Exception {
		
		//1414595313
		
		java.sql.Timestamp timeStampDate = new Timestamp(Long.parseLong(args[0]));  
		System.out.println("Time: "+timeStampDate.getTime());  
		//timestamp para date  
		Date outraData = new Date(timeStampDate.getTime());  
		System.out.println("Data :"+ outraData);
		
	}
}