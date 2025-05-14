package domein;

import java.util.ArrayList;
import java.util.List;

public class SiertuinPerceel extends TuinPerceel {
	private static final double EXTRA_WAARDE_PERK = 10;
	private final List<String> bloemenperken;
	private boolean vijverAanwezig;

	public SiertuinPerceel(String perceelsCode, List<String> bloemenperken, boolean vijverAanwezig) {
		this(perceelsCode, DEFAULT_LENGTE_BREEDTE, DEFAULT_LENGTE_BREEDTE, bloemenperken, vijverAanwezig);
	}

	public SiertuinPerceel(String perceelsCode, double lengte, double breedte, List<String> bloemenperken, boolean vijverAanwezig){
		super(lengte, breedte, perceelsCode);
		if (bloemenperken == null)
			bloemenperken = new ArrayList<>();
		if (bloemenperken.size() > geefOppervlakte()) {
			throw new IllegalArgumentException("Te veel bloemenperken voor dit perceel");
		}
		this.bloemenperken = bloemenperken;
		setVijverAanwezig(vijverAanwezig);
	}

	@Override
	public double geefWaarde() {
		return  bloemenperken.size() + (vijverAanwezig ? EXTRA_WAARDE_PERK : 0);
	}

	public List<String> getBloemenperken() {
		return this.bloemenperken;
	}
	
	public void voegBloemenperkToe(String bloemenperk) {
		bloemenperken.add(bloemenperk);
	}

	public boolean isVijverAanwezig() {
		return vijverAanwezig;
	}
		
	public final void setVijverAanwezig(boolean vijverAanwezig) {
		this.vijverAanwezig = vijverAanwezig;
	}

	@Override
	public String toString() {
		return String.format("%s %s vijver", super.toString(), vijverAanwezig ? "met" : "zonder");
	}
}