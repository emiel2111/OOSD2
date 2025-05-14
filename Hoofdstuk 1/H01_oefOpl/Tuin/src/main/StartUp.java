package main;

import domein.DomeinController;
import ui.TuinApp;

public class StartUp {

	public static void main(String[] args) {
		new TuinApp(new DomeinController()).start();
	}

}
