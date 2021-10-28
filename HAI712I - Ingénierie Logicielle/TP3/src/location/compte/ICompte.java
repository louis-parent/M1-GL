package location.compte;

import location.client.Client;
import location.produit.IProduit;

public interface ICompte
{
	public abstract Client getClient();
	
	public abstract double prixLocation(IProduit produit);
	public abstract double prixVente(IProduit produit);
}
