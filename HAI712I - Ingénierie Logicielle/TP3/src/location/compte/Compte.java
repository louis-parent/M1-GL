package location.compte;

import location.client.Client;
import location.produit.IProduit;

public class Compte implements ICompte
{
	private Client client;
	
	public Compte(Client client)
	{
		this.client = client;
	}
	
	@Override
	public Client getClient()
	{
		return this.client;
	}

	@Override
	public double prixLocation(IProduit produit)
	{
		return produit.getPrixLocation();
	}

	@Override
	public double prixVente(IProduit produit)
	{
		return produit.getPrixVente();
	}
}
