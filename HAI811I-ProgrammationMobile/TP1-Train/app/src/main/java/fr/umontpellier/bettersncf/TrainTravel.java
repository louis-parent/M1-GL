package fr.umontpellier.bettersncf;

public class TrainTravel
{
    public long id;

    public String departureTime;
    public String departureLocation;

    public String arrivalTime;
    public String arrivalLocation;

    public float price;

    public String duration;

    public int changes;

    public TrainTravel(long id, String departureTime, String departureLocation, String arrivalTime, String arrivalLocation, float price, String duration, int changes)
    {
        this.id = id;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.arrivalTime = arrivalTime;
        this.arrivalLocation = arrivalLocation;
        this.price = price;
        this.duration = duration;
        this.changes = changes;
    }
}
