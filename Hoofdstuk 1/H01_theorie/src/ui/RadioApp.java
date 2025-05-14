package ui;


//tag::codeExample[]
class Radio { // Superklasse
	public void speelMuziek() {
		System.out.println("De radio speelt muziek");
	}
}

class KlassiekeRadio extends Radio { // Subklasse
	@Override
	public void speelMuziek() {
		super.speelMuziek(); // <1>
		System.out.println("De klassieke radio speelt: Mozart");
	}
}

public class RadioApp {
	public static void main(String args[]) {
		KlassiekeRadio radio = new KlassiekeRadio();
		radio.speelMuziek();
	}
}
//end::codeExample[]
