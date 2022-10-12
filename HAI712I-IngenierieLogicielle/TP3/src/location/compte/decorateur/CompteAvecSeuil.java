package location.compte.decorateur;

import location.compte.ICompte;
import location.produit.IProduit;

public class CompteAvecSeuil extends DecorateurCompte
{
	private int seuil;
	private int nombreLocation;
	
	public CompteAvecSeuil(ICompte compte)
	{
		this(compte, 2);
	}
	
	public CompteAvecSeuil(ICompte compte, int seuil)
	{
		super(compte);
		this.seuil = seuil;
		this.nombreLocation = 0;
	}

	@Override
	public double prixLocation(IProduit produit)
	{		
		if(this.estGratuit())
		{
			this.nombreLocation++;
			return 0;
		}
		else
		{
			this.nombreLocation++;
			return this.getCompte().prixLocation(produit);
		}
	}

	@Override
	public double prixVente(IProduit produit)
	{
		return this.getCompte().prixVente(produit);
	}
	
	private boolean estGratuit()
	{
		if(this.nombreLocation == 0) // Evite la division par 0
		{
			return false;
		}
		else
		{
			return this.nombreLocation % this.seuil == 0;
		}
	}
}
