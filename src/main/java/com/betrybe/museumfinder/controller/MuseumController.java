package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller da plicação.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private MuseumServiceInterface service;

  /**
   * Constructor.
   */
  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Rota de post.
   */
  @PostMapping
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto museum) {
    Museum museumToDto = service.createMuseum(ModelDtoConverter.dtoToModel(museum));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ModelDtoConverter.modelToDto(museumToDto));
  }

  /**
   * Rota de Get.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam(name = "lat") double lat,
      @RequestParam(name = "lng") double lng,
      @RequestParam("max_dist_km") double maxDistKm) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum closestMuseum = service.getClosestMuseum(coordinate, maxDistKm);
    return ResponseEntity.ok(ModelDtoConverter.modelToDto(closestMuseum));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseumbyId(@PathVariable Long id) {
    Museum getMuseum = service.getMuseum(id);
    return ResponseEntity.ok((ModelDtoConverter.modelToDto(getMuseum)));
  }
}
