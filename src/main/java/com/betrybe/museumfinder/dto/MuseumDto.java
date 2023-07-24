package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * Classe tipo Dto.
 */
public record MuseumDto(
    Long id, String name,
    String description, String address,
    String collectionType, String subject,
    String url, Coordinate coordinate) {

}
