package main;

import domein.DomeinController;
import ui.FilmApplicatie;

public class StartUp {
    public static void main(String[] args) {
        
    	DomeinController dc = new DomeinController();
    	FilmApplicatie app = new FilmApplicatie(dc);
    	app.start();
    }
}
