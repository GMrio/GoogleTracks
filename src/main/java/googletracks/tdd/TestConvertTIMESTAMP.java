package googletracks.tdd;

import java.util.Date;

import com.google.api.client.util.DateTime;

public class TestConvertTIMESTAMP {
	public static void main(String[] args) {
		/*
		Converts conversor = new Converts();
		String novo = conversor.timestampGoogleForTimestamp("1.417346028E9");
		
		System.out.println(novo);
		
		*/
		
		
		//1417775515 = = Fri, 05 Dec 2014 10:31:55 GMT
		
		System.out.println(System.currentTimeMillis() / 1000L);
		
		System.out.println(new Date(1438433670L*1000));
	}

}
