package main;

import gui.Gui;

import java.io.IOException;
import java.util.List;

import utilities.Url;

public class Application {


	/*
	 *  wieviele Seiten sollen extrahiert werden
	 *  minimal 2
	 */
	private static Integer seiten = 5;
	
	/*
	 * online true
	 * offline false
	 */
	
	private static boolean online = false;
	
	public static void main(String[] args) throws InterruptedException, IOException {

		List<String> urlList = utilities.Utilities.buildURL("B008RHGC7S", seiten );
		
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
//		System.out.println("Produkt: " + produktName());
//		System.out.println("Review Texte.size: " + reviewTexte.size());
		System.out.println("Dursuchte url's:");
		for (String string : urlList) {
			System.out.println(string);
		}
		System.out.println("##############");
		utilities.Utilities.countWords("src/texts/reviewsList.txt");
		
		Gui window = new Gui();
		window.gui();
	}

	
}
