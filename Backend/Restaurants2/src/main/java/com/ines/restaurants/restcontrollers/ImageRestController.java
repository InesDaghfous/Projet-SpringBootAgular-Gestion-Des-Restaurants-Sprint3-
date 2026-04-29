package com.ines.restaurants.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.ines.restaurants.entities.Image;
import com.ines.restaurants.entities.Restaurant;
import com.ines.restaurants.service.ImageService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
    @Autowired
    ImageService imageService;

    @Autowired
    com.ines.restaurants.service.RestaurantService restaurantService;

    @PostMapping("/upload")
    public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageService.uplaodImage(file);
    }

    @PostMapping("/uplaodImageProd/{idProd}")
    public Image uploadMultiImages(@RequestParam("image") MultipartFile file,
            @PathVariable("idProd") Long idProd) throws IOException {
        return imageService.uplaodImageProd(file, idProd);
    }

    @GetMapping("/getImagesProd/{idProd}")
    public List<Image> getImagesProd(@PathVariable("idProd") Long idProd) throws IOException {
        return imageService.getImagesParProd(idProd);
    }

    @GetMapping("/get/info/{id}")
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
        return imageService.getImageDetails(id);
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
        return imageService.getImage(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping("/update")
    public Image updateImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageService.uplaodImage(file);
    }

    // ─── Stockage sur disque (Point 7 du TP) ──────────────────────

    @PostMapping(value = "/uploadFS/{id}")
    public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id)
            throws IOException {
        com.ines.restaurants.dto.RestaurantDTO r = restaurantService.getRestaurant(id);
        String imagePath = id + ".jpg";
        
        java.nio.file.Path path = java.nio.file.Paths.get(System.getProperty("user.home") + "/images/" + imagePath);
        java.nio.file.Files.createDirectories(path.getParent());
        java.nio.file.Files.write(path, file.getBytes());
        
        restaurantService.updateRestaurant(r);
    }

    @GetMapping(value = "/loadfromFS/{id}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
        String imagePath = id + ".jpg";
        return java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(System.getProperty("user.home") + "/images/" + imagePath));
    }
}
