package location.produit.decorateur;

import location.produit.IProduit;

public abstract class DecorateurProduit implements IProduit
{
	private IProduit produit;
	
	public DecorateurProduit(IProduit produit)
	{
		this.produit = produit;
	}
	
	@Override
	public String getNom()
	{
		return this.produit.getNom();
	}
	
	protected IProduit getProduit()
	{
		return this.produit;
	}
}
