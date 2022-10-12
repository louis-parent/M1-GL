package fr.umontpellier.Tp9.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

@Entity(name="authorities")
@AccessType(AccessType.Type.FIELD)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Authority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authority_id;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    private String authority;
}
