package com.ines.restaurants.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
    private Long idRestaurant;
    private String nomRestaurant;
    private Double noteRestaurant;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOuverture;
    
    private Long idSpec;     
    private String nomSpec;  
}

