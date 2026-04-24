package com.ines.restaurants.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Specialite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpec;
    private String nomSpec;
    private String descriptionSpec;

    @JsonIgnore
    @OneToMany(mappedBy = "specialite", fetch = FetchType.LAZY)
    private List<Restaurant> restaurants;
}