package googletracks.tdd;

import googletracks.controller.CrumbsController;

public class MainzinhaCrumbDayById {
	public static void main(String[] args) {
		
		CrumbsController crumbsController = new CrumbsController();
		crumbsController.retrieveCrumbsHistoryDayById("8939b628c04288674a", "1413923495", "1415894137");
		
//		String num = "1.414595012E9";
//		num = num.replace(".", "");
//		int x = num.indexOf('E');
//		String test = num.substring(0, x);
//		System.out.println(num);
//		System.out.println(x);
//		System.out.println(test);
		
	}
}
