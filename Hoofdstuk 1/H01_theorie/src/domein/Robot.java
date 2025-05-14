package domein;

//tag::codeExample[]

public class Robot {
	private final String type;
	private final int serieNummer; // Uniek serienummer
	
	public Robot(String type, int serieNummer) {
		super();
		this.type = type;
		this.serieNummer = serieNummer;
	}
	
	@Override
	public int hashCode() { // <2>
		final int prime = 31;
		int result = 1;
		result = prime * result + serieNummer; 
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (serieNummer != other.serieNummer) // <1>
			return false;
		return true;
	}
}
//end::codeExample[]
