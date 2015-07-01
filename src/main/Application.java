package main;


import gui.GuiSwt;

import java.io.IOException;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {


		// SWT Version von GUI
		GuiSwt window = new GuiSwt();
		window.gui();
		
	}
}
