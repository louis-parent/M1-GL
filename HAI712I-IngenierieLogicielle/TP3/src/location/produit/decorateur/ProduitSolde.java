package location.produit.decorateur;

import location.produit.IProduit;

public class ProduitSolde extends DecorateurProduit
{
	private double pourcent;
	
	public ProduitSolde(IProduit produit)
	{
		this(produit, 50);
	}
	
	public ProduitSolde(IProduit produit, double pourcent)
	{
		super(produit);
		this.pourcent = pourcent;
	}

	@Override
	public double getPrixVente()
	{
		return this.getProduit().getPrixVente() * (this.pourcent / 100);
	}
}
