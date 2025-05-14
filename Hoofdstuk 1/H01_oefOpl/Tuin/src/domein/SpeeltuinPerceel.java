package domein;

public class SpeeltuinPerceel extends TuinPerceel {
	private static final double WAARDE = 2.5;
	private final int aantalToestellen;

	public SpeeltuinPerceel(int aantalToestellen, String perceelsCode) {
		this(DEFAULT_LENGTE_BREEDTE, DEFAULT_LENGTE_BREEDTE, aantalToestellen, perceelsCode);
	}

	public SpeeltuinPerceel(double lengte, double breedte, int aantalToestellen, String perceelsCode) {
		super(lengte, breedte, perceelsCode);
		if (aantalToestellen * 3 > geefOppervlakte())
			throw new IllegalArgumentException("Er past niet meer dan 1 toestel per 3 vierkante meter");
		this.aantalToestellen = aantalToestellen;
	}

	@Override
	public double geefWaarde() {
		return aantalToestellen * WAARDE;
	}

	public int getAantalToestellen() {
		return aantalToestellen;
	}

	@Override
	public String toString() {
		return String.format("%s en met %d toestellen om te spelen.", super.toString(), aantalToestellen);
	}
}