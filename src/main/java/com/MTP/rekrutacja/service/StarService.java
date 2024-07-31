package com.MTP.rekrutacja.service;

import com.MTP.rekrutacja.dto.StarDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Service interface for managing stars.
 */
public interface StarService {


    StarDto findById(Long id);

    StarDto addStar(StarDto starDto);

    StarDto updateStar(Long id, StarDto starDto);

    void deleteStar(Long id);

    List<StarDto> findClosestStars(List<StarDto> stars, int size);

    Map<Long, Integer> getNumberOfStarsByDistances(List<StarDto> stars);

    Collection<StarDto> getUniqueStars(Collection<StarDto> stars);

    List<StarDto> findAllStars();
}