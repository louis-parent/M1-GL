package fr.umontpellier.TP7;

public class Registration
{
    private String email;
    private String nom;
    private String prenom;

    public Registration()
    {
        this("", "", "");
    }

    public Registration(String email, String nom, String prenom)
    {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
}
