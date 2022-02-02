package location.compte.decorateur;

import location.compte.ICompte;
import location.produit.IProduit;

public class CompteAvecReduction extends DecorateurCompte
{
	private double reduction;
	
	public CompteAvecReduction(ICompte compte)
	{
		this(compte, 50);
	}
	
	public CompteAvecReduction(ICompte compte, double reduction)
	{
		super(compte);
		this.reduction = reduction;
	}

	@Override
	public double prixLocation(IProduit produit)
	{
		return this.avecReduction(this.getCompte().prixLocation(produit));
	}

	@Override
	public double prixVente(IProduit produit)
	{
		return this.avecReduction(this.getCompte().prixVente(produit));
	}
	
	private double avecReduction(double prix)
	{
		return prix * (this.reduction / 100);
	}
}
