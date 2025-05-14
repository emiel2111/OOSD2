package domein;

import java.util.List;

public class DomeinController {
	private final RekeningRepository rekeningRepos;

	public DomeinController() {
		rekeningRepos = new RekeningRepository();
	}

	public void wijzigRekeningen() {
		rekeningRepos.wijzigRekeningen();
	}
	
	public String[] geefRekeningen() {
		// zet lijst van Rekeningen om in een String array
		List<Rekening> lijst = rekeningRepos.getRekeningen();
		
		
		String[] rekSArray = new String[lijst.size()];
		int teller = 0;
		for (Rekening r : lijst)
			rekSArray[teller++]=r.toString();
		return rekSArray;
	}

	public double[][] geefSaldoEnMaxKredietOnderNul() {

		List<Rekening> lijst = rekeningRepos.geefZichtrekeningen();
		double[][] infoBedragen = new double[lijst.size()][2];

		int teller = 0;
		for (Rekening r : lijst) {
			if (r instanceof ZichtRekening zr) {
				infoBedragen[teller][0] = zr.getSaldo();
				infoBedragen[teller][1] = zr.getMaxKredietOnderNul();
				teller++;
			}
		}
		
		return infoBedragen;
	}

}
