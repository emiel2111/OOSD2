package domeinHuisdier;

import java.util.ArrayList;
import java.util.List;

public class DierenWinkel {
    List<Kat> deKatten = new ArrayList<>();
    List<Hond> deHonden = new ArrayList<>();
        
    public DierenWinkel() {
    	deKatten.add(new Kat("Garfield"));
    	deKatten.add(new Kat("Pluche"));
        
    	deHonden.add(new Hond("Nala"));
    	deHonden.add(new Hond("Kamiel"));
    }
    
    public void maakKennis() {
        for (Hond h: deHonden) {
            System.out.printf("%s: %s%n",
                    h.getNaam(), 
                    h.maakGeluid());
        }
        
        for (Kat k: deKatten) {
            System.out.printf("%s: %s%n",
                    k.getNaam(), 
                    k.maakGeluid());
        }
    }
}
