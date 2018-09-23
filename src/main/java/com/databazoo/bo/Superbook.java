package com.databazoo.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Superbook business object.
 */
@Setter
@Getter
@Entity
@Table(name = "superbook")
public class Superbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    private Calendar publicationDate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = true)
    private String author;

    @ManyToOne(/*targetEntity = Publisher.class,*/ fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name="actor_to_superbook",
            joinColumns=@JoinColumn(name="superbook_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id"))
    private List<Actor> actors = new ArrayList<>();

    public Superbook() {
    }

    public Superbook(String title) {
        this.title = title;
    }
}
