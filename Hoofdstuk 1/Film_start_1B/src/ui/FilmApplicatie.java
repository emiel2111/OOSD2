package ui;

import java.util.Scanner;

import domein.DomeinController;

public class FilmApplicatie {

	private final DomeinController dc;

	public FilmApplicatie(DomeinController dc) {
		this.dc = dc;
	}

	public void start() {

		int keuze = maakKeuzeUitMenu();
		while (keuze != 4) {

			switch (keuze) {
			case 1 -> drukOverzichtFilmsAf();
			case 2 -> voegFilmToe();
			case 3 -> System.out.printf("Aantal films: %d%n", geefAantalFilms());
			}

			keuze = maakKeuzeUitMenu();
		}

	}

	public int maakKeuzeUitMenu() {
		Scanner invoer = new Scanner(System.in);

		int keuze;
		do {
			drukKeuzeMenuAf();
			keuze = invoer.nextInt();
		} while (keuze < 1 || keuze > 4);

		return keuze;
	}

	public void drukOverzichtFilmsAf() {
		System.out.println("Overzicht films:");
		for (String film : dc.geefAlleFilms())
			System.out.println(film);
	}

	public void voegFilmToe() {
		Scanner invoer = new Scanner(System.in);
		
		System.out.print("Geef de naam van de film: ");
		String naam = invoer.nextLine();
		
		System.out.print("Geef het jaar waarin de film uitkwam: ");
		int jaar = invoer.nextInt();
		
		System.out.print("Hoeveel sterren verdient deze film: ");
		int sterren = invoer.nextInt();
		
		dc.voegFilmToe(naam,jaar,sterren);
	}

	public int geefAantalFilms() {
		return dc.geefAantalFilms();
	}

	public void drukKeuzeMenuAf() {

		System.out.println("Maak een keuze:");
		System.out.println("1. Toon het overzicht van de films");
		System.out.println("2. Voeg een film toe");
		System.out.println("3. Geef het aantal films in de applicatie");
		System.out.println("4. Stop de applicatie");

	}
}
