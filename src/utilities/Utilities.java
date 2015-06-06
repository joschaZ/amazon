package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
	
	public static List<String> buildURL(String produkt, Integer seiten) throws InterruptedException, IOException{

		List<String> urlList = new ArrayList<String>();
		
		
		for(int i = 1; i <= seiten; i++){
			String string = "http://www.amazon.com/AH1650SR-Feet-16-Gauge-Speaker-Wire/product-reviews/B0029HO66G/ref=cm_cr_pr_btm_link_2?ie=UTF8&showViewpoints=1&sortBy=helpful&reviewerType=all_reviews&formatType=all_formats&filterByStar=all_stars&pageNumber=" + i;
			urlList.add(string);
		}
		return urlList;
	}
	
	/*
	 * Es wird immer nur der Quelltext der letzten, von "buildURL" übergebenen Seite gezogen
	 */
	public static void getQuelltext(String url) throws IOException, InterruptedException {
		
		File fileQuelltext = new File("src/texts/Quelltext.txt");
		
		//TODO: richtges File Managment
		
//		if(fileQuelltext.exists()){
//			fileQuelltext.delete();
//			fileQuelltext.createNewFile();
//		}
		
		URL website = new URL("" + url + "");
		InputStreamReader isr = new InputStreamReader(website.openStream());
		BufferedReader br = new BufferedReader(isr);
		
		
		FileWriter out = new FileWriter(fileQuelltext, true);
		BufferedWriter bw = new BufferedWriter(out);
		
		
		String inputLine;
		while ((inputLine = br.readLine()) != null) {
			if (!inputLine.isEmpty()) {
				bw.append(inputLine)
				.append("\n");
			}
		}
		br.close();
		bw.close();
	}
	
	public static void countWords (String string2) throws IOException{
		
		int i = 0;
		String line = ""; 
		String line2 = ""; 
		
		BufferedReader br = new BufferedReader(new FileReader("src/texts/reviewsList.txt"));
		    while((line = br.readLine()) != null ) {
		    	i++;
		    }
		    System.out.println("Insgesamt " + i + " Wörter in der Liste");
		
		}
}
