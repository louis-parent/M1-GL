package location.produit;

public interface IProduit
{
	public abstract String getNom();	
	public abstract double getPrixVente();

	public default double getPrixLocation()
	{
		return this.getPrixVente() / 2;
	}
}
