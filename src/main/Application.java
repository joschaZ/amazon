package main;

import gui.Gui;

import java.io.IOException;
import java.util.List;

import utilities.Url;
import utilities.Utilities;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {

		
//		Utilities ut = new Utilities();
//		ut.wordOccurencies();
//		
		Gui window = new Gui();
		window.gui();
	}
}
