package location;

import location.client.Client;
import location.compte.Compte;
import location.compte.decorateur.CompteAvecReduction;
import location.compte.decorateur.CompteAvecSeuil;
import location.produit.IProduit;
import location.produit.Produit;
import location.produit.decorateur.ProduitSolde;

public class Main
{
	private static final IProduit laGrandeVadrouille = new Produit("La grande vadrouille", 10.0);
	private static final IProduit rocky4 = new ProduitSolde(new Produit("RockyIV", 10.0));
	private static final Client dupont = new Client("Dupont");
	
	public static void main(String[] args)
	{
		Main.testCompte();
		Main.testCompteReduction();
		Main.testCompteSeuil();
		Main.testCompteReductionSeuil();
	}
	
	private static void testCompte()
	{
		Compte compte = new Compte(dupont);
		
		assert compte.prixLocation(laGrandeVadrouille) == 5.0;
		System.out.println("Compte Normal : PASSED");
		
		assert compte.prixLocation(rocky4) == 2.5;
		System.out.println("Compte Normal + Produit Solde : PASSED");
	}
	
	private static void testCompteReduction()
	{
		CompteAvecReduction compteReduction = new CompteAvecReduction(new Compte(dupont));
		
		assert compteReduction.prixLocation(laGrandeVadrouille) == 2.5;
		System.out.println("Compte Reduction : PASSED");
		
		assert compteReduction.prixLocation(rocky4) == 1.25;
		System.out.println("Compte Reduction + Produit Solde : PASSED");
	}
	
	private static void testCompteSeuil()
	{
		CompteAvecSeuil compteSeuil = new CompteAvecSeuil(new Compte(dupont)); // le seuil est `a 2 par d ́efaut
		
		assert compteSeuil.prixLocation(laGrandeVadrouille) == 5;
		System.out.println("Compte Seuil : PASSED");
		
		assert compteSeuil.prixLocation(laGrandeVadrouille) == 5;
		System.out.println("Compte Seuil : PASSED");
		
		assert compteSeuil.prixLocation(laGrandeVadrouille) == 0;
		System.out.println("Compte Seuil + Gratuit : PASSED");
	}
	
	private static void testCompteReductionSeuil()
	{
		CompteAvecReduction compteReductionSeuil = new CompteAvecReduction(new CompteAvecSeuil(new Compte(dupont))); // le seuil est `a 2 par d ́efaut
		
		assert compteReductionSeuil.prixLocation(laGrandeVadrouille) == 2.5;
		System.out.println("Compte Seuil & Reduction : PASSED");
		
		assert compteReductionSeuil.prixLocation(rocky4) == 1.25;
		System.out.println("Compte Seuil & Reduction + Produit Solde : PASSED");
		
		assert compteReductionSeuil.prixLocation(rocky4) == 0;
		System.out.println("Compte Seuil & Reduction + Produit Solde + Gratuit : PASSED");
	}
}
