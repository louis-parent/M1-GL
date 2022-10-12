package fr.umontpellier.tp2;

public class SensorType
{
    private int id;
    private String name;

    public SensorType(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
}
