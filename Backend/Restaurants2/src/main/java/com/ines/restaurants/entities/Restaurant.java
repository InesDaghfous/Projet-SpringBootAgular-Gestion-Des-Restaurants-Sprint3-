package com.ines.restaurants.entities;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;
    private String nomRestaurant;
    private Double noteRestaurant;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOuverture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("restaurants")
    private Specialite specialite;

    @OneToMany(mappedBy = "restaurant")
    private List<Image> images;

    public Restaurant() {
        super();
    }

    public Restaurant(String nomRestaurant, Double noteRestaurant, Date dateOuverture) {
        super();
        this.nomRestaurant = nomRestaurant;
        this.noteRestaurant = noteRestaurant;
        this.dateOuverture = dateOuverture;
    }

    public Long getIdRestaurant() { return idRestaurant; }
    public void setIdRestaurant(Long idRestaurant) { this.idRestaurant = idRestaurant; }

    public String getNomRestaurant() { return nomRestaurant; }
    public void setNomRestaurant(String nomRestaurant) { this.nomRestaurant = nomRestaurant; }

    public Double getNoteRestaurant() { return noteRestaurant; }
    public void setNoteRestaurant(Double noteRestaurant) { this.noteRestaurant = noteRestaurant; }

    public Date getDateOuverture() { return dateOuverture; }
    public void setDateOuverture(Date dateOuverture) { this.dateOuverture = dateOuverture; }

    public Specialite getSpecialite() { return specialite; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }

    public List<Image> getImages() { return images; }
    public void setImages(List<Image> images) { this.images = images; }

    @Override
    public String toString() {
        return "Restaurant [idRestaurant=" + idRestaurant + ", nomRestaurant=" + nomRestaurant
                + ", noteRestaurant=" + noteRestaurant + ", dateOuverture=" + dateOuverture + "]";
    }
}