package fr.umontpellier.TP8.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="locations")
@AccessType(AccessType.Type.FIELD)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;

    private double latitude;
    private double longitude;
    private Date location_date;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users;

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Date location_date) {
        this.location_date = location_date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
