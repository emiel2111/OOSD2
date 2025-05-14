package domein;

import java.util.ArrayList;
import java.util.List;

public class DomeinController {
	private  Tuin tuin;
	private final TuinPerceelRepository tuinPerceelRepo;
	
	public DomeinController() {
		tuinPerceelRepo = new TuinPerceelRepository();
	}
	
	public void maakTuin() {
		tuin = new Tuin(tuinPerceelRepo.geefTuinPercelen());
	}


	public List<String> geefAlleSiertuinpercelen() {
		List<SiertuinPerceel> percelen = tuin.geefAlleSiertuinPercelen();
		List<String> siertuinPercelen = new ArrayList<>();
		
		for (SiertuinPerceel s : percelen)
			siertuinPercelen.add(s.toString());
		
		return siertuinPercelen;
		
	}
	
	public void voegSpeeltuinPerceelToe(double lengte, double breedte, int aantalToestellen, String perceelsCode) {
		tuin.voegPerceelToe(new SpeeltuinPerceel(lengte,breedte,aantalToestellen,perceelsCode));
	}

	public int geefAantalPercelen() {
		return tuin.geefAantalPercelen();
	}
	
	public double geefTotaleWaardePercelen() {
		return tuin.geefTotaleWaardePercelen();
	}
}