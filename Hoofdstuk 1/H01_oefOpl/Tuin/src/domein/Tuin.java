package domein;

import java.util.ArrayList;
import java.util.List;


public class Tuin {

	private final List<TuinPerceel> tuinpercelen;

	public Tuin(List<TuinPerceel> tuinpercelen)  {
		this.tuinpercelen = new ArrayList<>(tuinpercelen);
	}


	public List<SiertuinPerceel> geefAlleSiertuinPercelen() {
		List<SiertuinPerceel> siertuinen = new ArrayList<>();
		for (TuinPerceel tp : tuinpercelen)
			if (tp instanceof SiertuinPerceel sp)
				siertuinen.add(sp);
		
		return siertuinen;
	}
	
	public void voegPerceelToe(TuinPerceel tp) {
		if (tuinpercelen.contains(tp))
			throw new IllegalArgumentException("Dit perceel behoort al tot de tuin.");
		tuinpercelen.add(tp);
	}
	
	public int geefAantalPercelen() {
		return tuinpercelen.size();
	}
	
	public double geefTotaleWaardePercelen() {
		double som=0;
		for (TuinPerceel p : tuinpercelen)
			som += p.geefWaarde();
		return som;
	}
}