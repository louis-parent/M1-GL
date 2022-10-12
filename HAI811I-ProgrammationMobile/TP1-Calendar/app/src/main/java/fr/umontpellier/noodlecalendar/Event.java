package fr.umontpellier.noodlecalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Event
{
    private static int nextId = 0;

    public static final List<Event> EVENTS = new ArrayList<Event>(){{
        this.add(new Event(2022, 2, 3, "17h30", "RDV Médical", "Rendez-vous au cabinet de M. X", "Montpellier"));
        this.add(new Event(2022, 2, 14, "11h10", "RDV Orange", "Intallation de la fibre", "Montpellier"));
        this.add(new Event(2022, 2, 14, "20h15", "Repas de Famille", "Anniverssaire de Y", "Saint Jean de Vedas"));
        this.add(new Event(2022, 2, 30, "8h20", "Démarchage de P", "Rendez-vous avec un prospect P", "Perpignan"));
        this.add(new Event(2022, 2, 30, "9h45", "Démarchage de R", "Rendez-vous avec un prospect R", "Perpignan"));
        this.add(new Event(2022, 2, 30, "12h30", "Repas d'Affaire", "Repas avec A, B, C pour parler affaire", "Montpellier"));
    }};

    public long id;
    public int year;
    public int month;
    public int day;
    public String time;
    public String title;
    public String description;
    public String location;

    public Event(int year, int month, int day, String time, String title, String description, String location)
    {
        this.id = Event.nextId++;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public static List<Event> getAll()
    {
        return Event.EVENTS;
    }

    public static List<Event> getAt(int year, int month, int day)
    {
        List<Event> filtered = new ArrayList<Event>();

        for(Event event : Event.EVENTS)
        {
            if(event.year == year && event.month == month && event.day == day)
            {
                filtered.add(event);
            }
        }

        return filtered;
    }

    public static void add(Event event)
    {
        Event.EVENTS.add(event);
    }
}
