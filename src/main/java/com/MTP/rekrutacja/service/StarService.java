package com.MTP.rekrutacja.service;

import com.MTP.rekrutacja.dto.StarDto;
import com.MTP.rekrutacja.model.Star;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface StarService {
    List<StarDto> findClosestStars(List<StarDto> stars, int size);

    Map<Long, Integer> getNumberOfStarsByDistances(List<StarDto> stars);

    Collection<Star> getUniqueStars(Collection<Star> stars);

    StarDto findById(Long id);

    StarDto addStar(StarDto starDto);

    StarDto updateStar(Long id, StarDto starDto);

    void deleteStar(Long id);

    List<StarDto> findAllStars();
}