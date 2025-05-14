package ui;

import domein.DomeinController;

public class RekeningApplicatie 
{
	private final DomeinController dc;
	
	public RekeningApplicatie(DomeinController dc) 
	{
		this.dc = dc;
	}

	public void werkMetRekeningen()
	{
		// hier gebruik je de domeincontroller om met de domeinlaag te communiceren
		System.out.println("Overzicht rekeningen");
		String[] rekn = dc.geefRekeningen();
		for (String rek : rekn)
			System.out.println(rek);
		
		System.out.println();
		
		System.out.println("Wijzig rekeningen");
		dc.wijzigRekeningen();
				
		double[][] bedragenZichtrekeningen = dc.geefSaldoEnMaxKredietOnderNul();
		
		int teller = 1;
		for (double[] rij : bedragenZichtrekeningen )
			System.out.printf("Zichtrekening %d heeft als saldo %.2f en mag %.2f onder nul.%n", teller++, rij[0], Math.abs(rij[1]) );
		System.out.println();
		
		
		System.out.println("Overzicht codes rekeningen");
		for(String code: dc.geefCodes())
		{
			System.out.println(code);
		}

	}
}
