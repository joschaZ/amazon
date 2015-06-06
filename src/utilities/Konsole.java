package utilities;

import java.io.IOException;
import java.util.List;

public class Konsole {
	
	
	private static boolean online = true;
	
	
	public static void control(String link, int seiten) throws InterruptedException, IOException{

		link.substring(0, link.length()-1);
		
		List<String> urlList = utilities.Utilities.buildURL(link, seiten );
		
		
		
	// "B008RHGC7S"	
		
	
	if(online == true){
		for (String string : urlList) {
			System.out.println("Quelltext ziehen.....");
			utilities.Utilities.getQuelltext(string);
			Thread.sleep(500);
		}
	}
	Url.reviewTextToFile();
	Url.reviewToToken();
      
	System.out.println("##############");
//	System.out.println("Produkt: " + produktName());
//	System.out.println("Review Texte.size: " + reviewTexte.size());
	System.out.println("Dursuchte url's:");
	for (String string : urlList) {
		System.out.println(string);
	}
	System.out.println("##############");
	utilities.Utilities.countWords("src/texts/reviewsList.txt");
	}
}
