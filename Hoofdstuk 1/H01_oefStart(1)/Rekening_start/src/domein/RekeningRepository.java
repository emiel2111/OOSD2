package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.RekeningMapper;

public class RekeningRepository 
{
	private final List<Rekening> rekeningen;
	private final RekeningMapper rm;
	
	public RekeningRepository() 
	{
		rm = new RekeningMapper();
		rekeningen = rm.geefRekeningen();
	}

	public List<Rekening> geefZichtrekeningen() {
		
		List<Rekening> zichtrekeningen = new ArrayList<>();
		for (Rekening r : rekeningen) {
			if (r instanceof ZichtRekening)
				zichtrekeningen.add(r);
		}
		return zichtrekeningen;
	}
	
	
	public List<Rekening> getRekeningen() {
		return rekeningen;
	}

	public List<Rekening> wijzigRekeningen() 
	{
		for (Rekening r: rekeningen)
		{
			if (r instanceof SpaarRekening)
			{
				// intrest toevoegen!
				double verhoging = 
					r.getSaldo() * SpaarRekening.getAangroeiIntrest() / 100;
				r.stortOp(verhoging);
			}
			else if (r instanceof ZichtRekening zr)
			{
				zr.setMaxKredietOnderNul(zr.getMaxKredietOnderNul() - 10);
			}
		}
		return rekeningen;
	}
}