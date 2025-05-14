package domein;

import java.util.Objects;

public abstract class TuinPerceel {
	public final static double DEFAULT_LENGTE_BREEDTE = 5;
	private final String perceelsCode;
	private final double lengte;
	private final double breedte;

	public TuinPerceel(String perceelsCode) {
		this(DEFAULT_LENGTE_BREEDTE, DEFAULT_LENGTE_BREEDTE, perceelsCode);
	}

	public TuinPerceel(double lengte, double breedte, String perceelsCode) {
		controleerLengteEnBreedte(lengte, breedte);
		this.lengte = lengte;
		this.breedte = breedte;
		this.perceelsCode = perceelsCode;
	}
	
	private void controleerLengteEnBreedte(double lengte, double breedte) {
		if (lengte < 1 || breedte < 1)
			throw new IllegalArgumentException("Lengte en breedte moeten groter of gelijk aan 1 zijn");
		if (lengte < breedte)
			throw new IllegalArgumentException("Lengte moet groter dan of gelijk aan breedte zijn");
	}
	
	public double geefOppervlakte() {
		return lengte * breedte;
	}
	
	public abstract double geefWaarde();

	@Override
	public String toString() {
		return String.format("%s van %.1f op %.1f met een waarde van %.2f", getClass().getSimpleName(), lengte, breedte,
				geefWaarde());
	}

	@Override
	public int hashCode() {
		return Objects.hash(perceelsCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuinPerceel other = (TuinPerceel) obj;
		return Objects.equals(perceelsCode, other.perceelsCode);
	}

	public double getLengte() {
		return lengte;
	}

	public double getBreedte() {
		return breedte;
	}

	public String getPerceelsCode() {
		return perceelsCode;
	}
	

}