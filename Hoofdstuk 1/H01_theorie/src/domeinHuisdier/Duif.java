package domeinHuisdier;

public class Duif extends Huisdier
{
	private final long ringnr;
	
	public Duif(String naam, long ringnr)
	{
		super(naam);
		controleerRingnr(ringnr);
		this.ringnr = ringnr;
	}
	
	public String maakGeluid() 
	{
		return "roekoekoe";
	}

	public long getRingnr() 
	{
		return ringnr;
	}

	private void controleerRingnr(long ringnr) 
	{
		if (ringnr <= 19000000000L || ringnr > 99999999999L)
			throw new IllegalArgumentException("Geen geldig ringnummer");
	}

	public String toString() // <.>
	{
		return String.format("%s met ringnummer %d", super.toString(), ringnr);
	}
}
