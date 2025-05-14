package ui;

import java.util.List;
import java.util.Scanner;
import domein.DomeinController;

public class TuinApp {
	private final DomeinController domeinCtrl;

	public TuinApp(DomeinController domeinCtrl) {
		this.domeinCtrl = domeinCtrl;
	}

	public void start() {

		domeinCtrl.maakTuin();
		toonEnReageerOpMenu();
	}

	private void toonEnReageerOpMenu() {
		String[] keuzes = { "Toon alle siertuinpercelen", "Voeg speeltuinperceel toe", "Geef aantal percelen", "Geef totale waarde percelen", "Stop" };
		int keuze = maakKeuzeUitMenu(keuzes);
		while (keuze != keuzes.length) {
			switch (keuze) {
			case 1 -> toonAlleSiertuinpercelen();

			case 2 -> domeinCtrl.voegSpeeltuinPerceelToe(10, 5, 3, "SP007");
			
			case 3 -> toonAantalPercelen();
			
			case 4 -> toonTotaleWaardePercelen();

			}
			
			keuze = maakKeuzeUitMenu(keuzes);
		}
	}

	private void toonAlleSiertuinpercelen() {
		System.out.println("\nAlle siertuinpercelen:");
		List<String> percelen =  domeinCtrl.geefAlleSiertuinpercelen();
		for (String p : percelen)
			System.out.println(p);
	}
	
	private void toonAantalPercelen() {
		System.out.printf("Aantal percelen in de tuin: %d%n", domeinCtrl.geefAantalPercelen());
	}
	
	private void toonTotaleWaardePercelen() {
		System.out.printf("Totale waarde percelen in de tuin: %.2f%n", domeinCtrl.geefTotaleWaardePercelen());
	}
	
	private int maakKeuzeUitMenu(String[] keuzes) {
		int keuze = 0;
		Scanner invoer = new Scanner(System.in);
		do {
			
				System.out.println("\nMENU");
				System.out.println("=====");
				for (int i = 0; i < keuzes.length; ++i) {
					System.out.println(String.format("%d. %s", i + 1, keuzes[i]));
				}
				System.out.printf("Je keuze (1-%d): ", keuzes.length);
				keuze = invoer.nextInt();
				if (keuze < 1 || keuze > keuzes.length) {
					System.out.printf("%nGeef een getal in tussen 1 en %d%n", keuzes.length);
					keuze = 0;
				}
		} while (keuze == 0);
		return keuze;
	}
}
