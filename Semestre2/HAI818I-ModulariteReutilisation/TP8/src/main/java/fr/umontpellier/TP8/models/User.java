package fr.umontpellier.TP8.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.util.List;

@Entity(name="users")
@AccessType(AccessType.Type.FIELD)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String password;

    @ManyToMany
    @JoinTable(name="user_locations", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<Location> locations;

    public void setUser_id(Long id)
    {
        this.user_id = id;
    }

    public Long getUser_id()
    {
        return this.user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
