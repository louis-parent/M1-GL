package location.compte.decorateur;

import location.client.Client;
import location.compte.ICompte;

public abstract class DecorateurCompte implements ICompte
{
	private ICompte compte;
	
	public DecorateurCompte(ICompte compte)
	{
		this.compte = compte;
	}
	
	@Override
	public Client getClient()
	{
		return this.compte.getClient();
	}
	
	protected ICompte getCompte()
	{
		return this.compte;
	}
}
