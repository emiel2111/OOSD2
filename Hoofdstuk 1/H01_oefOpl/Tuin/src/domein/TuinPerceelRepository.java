package domein;

import java.util.List;

import persistentie.TuinPerceelMapper;

public class TuinPerceelRepository {
	public List<TuinPerceel> geefTuinPercelen()  {
		return new TuinPerceelMapper().geefTuinPercelen();
	}
}
