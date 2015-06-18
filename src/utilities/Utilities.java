package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {
	
	public static List<String> buildURL(String produkt, Integer seiten) throws InterruptedException, IOException{

		List<String> urlList = new ArrayList<String>();
		
		
		for(int i = 1; i <= seiten; i++){
			String string = produkt + i;
			urlList.add(string);
		}
		return urlList;
	}
	
	public static void getQuelltext(String url) throws IOException, InterruptedException {
		
		File fileQuelltext = new File("src/texts/Quelltext.txt");
		
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
	
	public static void wordOccurencies() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("src/texts/reviewsList.txt"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String line;
		while ((line = br.readLine()) != null) {
			Integer i = 1;
			if(!map.containsKey(line)){
				map.put(line, i);
			}
			else if(map.containsKey(line)){
//				int val = map.get(line);
//				val = val + 1;
				map.put(line, map.get(line) + 1);
				
			}
			
			i++;
		}
//		System.out.println(map.size());
//		for (String key : map.keySet()) {
//	        System.out.println(key + " " + map.get(key));
//	    }
		
		List<Map.Entry<String,Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
	    Collections.sort(entries,	new Comparator<Map.Entry<String,Integer>>() {
	    		public int compare(Map.Entry<String,Integer> a, Map.Entry<String,Integer> b) {
	    			return Integer.compare(b.getValue(), a.getValue());
	    		}
	    	}
	    );

	    
	    for (Map.Entry<String,Integer> e : entries) {
	    	System.out.println(e.getKey()+":"+e.getValue());
	    	
	    	
	    }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void countWords (String string2) throws IOException{
//		
//		int i = 0;
//		String line = ""; 
//		String line2 = ""; 
//		
//		BufferedReader br = new BufferedReader(new FileReader("src/texts/reviewsList.txt"));
//		    while((line = br.readLine()) != null ) {
//		    	i++;
//		    }
//		    System.out.println("Insgesamt " + i + " Wörter in der Liste");
//		
//		}
	
	
}
