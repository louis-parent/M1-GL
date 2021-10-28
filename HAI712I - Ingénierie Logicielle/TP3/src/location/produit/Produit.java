package location.produit;

public class Produit implements IProduit
{
	private String nom;
	private double prix;
	
	public Produit(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	@Override
	public String getNom()
	{
		return this.nom;
	}
	
	@Override
	public double getPrixVente()
	{
		return this.prix;
	}
}
