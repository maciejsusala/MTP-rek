package com.MTP.rekrutacja.service.impl;

import com.MTP.rekrutacja.dto.StarDto;
import com.MTP.rekrutacja.exception.StarNotFoundException;
import com.MTP.rekrutacja.model.Star;
import com.MTP.rekrutacja.repository.StarRepository;
import com.MTP.rekrutacja.service.StarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service implementation for managing stars.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;
    private final ObjectMapper objectMapper;

    /**
     * Finds a star by its ID.
     *
     * @param id the ID of the star
     * @return the StarDto containing star details
     * @throws StarNotFoundException if the star is not found
     */
    @Override
    public StarDto findById(Long id) {
        Star starEntity = starRepository.findById(id)
                .orElseThrow(() -> new StarNotFoundException("Star not found with id " + id));

        return objectMapper.convertValue(starEntity, StarDto.class);
    }

    /**
     * Adds a new star.
     *
     * @param starDto the StarDto containing star details
     * @return the StarDto containing star details
     */
    @Override
    @Transactional
    public StarDto addStar(StarDto starDto) {
        log.info("Adding new star with details {}", starDto);
        Star star = objectMapper.convertValue(starDto, Star.class);
        Star savedStar = starRepository.save(star);
        return objectMapper.convertValue(savedStar, StarDto.class);
    }

    /**
     * Updates an existing star.
     *
     * @param id the ID of the star to update
     * @param starDto the StarDto containing updated star details
     * @return the updated StarDto
     * @throws StarNotFoundException if the star is not found
     */
    @Override
    @Transactional
    public StarDto updateStar(Long id, StarDto starDto) {
        log.info("Updating star with id {} with new details {}", id, starDto);
        Star existingStar = starRepository.findById(id)
                .orElseThrow(() -> new StarNotFoundException("Star not found with id " + id));

        existingStar.setDistance(starDto.distance());
        // update other fields as necessary

        Star updatedStar = starRepository.save(existingStar);
        return objectMapper.convertValue(updatedStar, StarDto.class);
    }

    /**
     * Deletes a star by its ID.
     *
     * @param id the ID of the star to delete
     * @throws StarNotFoundException if the star is not found
     */
    @Override
    @Transactional
    public void deleteStar(Long id) {
        log.info("Deleting star with id {}", id);
        Star star = starRepository.findById(id)
                .orElseThrow(() -> new StarNotFoundException("Star not found with id " + id));
        starRepository.delete(star);
    }

    /**
     * Finds the closest stars.
     *
     * @param stars the list of StarDto objects
     * @param size the number of closest stars to return
     * @return the list of closest StarDto objects
     */
    @Override
    public List<StarDto> findClosestStars(List<StarDto> stars, int size) {
        return stars.stream()
                .sorted(Comparator.comparingLong(StarDto::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    /**
     * Gets the number of stars by distances.
     *
     * @param stars the list of StarDto objects
     * @return a map of distances to the number of stars
     */
    @Override
    public Map<Long, Integer> getNumberOfStarsByDistances(List<StarDto> stars) {
        return stars.stream()
                .collect(Collectors.groupingBy(
                        StarDto::getDistance,
                        TreeMap::new,
                        Collectors.summingInt(star -> 1)
                ));
    }

    /**
     * Gets unique stars.
     *
     * @param stars the collection of StarDto objects
     * @return a collection of unique StarDto objects
     */
    @Override
    public Collection<StarDto> getUniqueStars(Collection<StarDto> stars) {
        return stars.stream()
                .collect(Collectors.toMap(StarDto::getName, star -> star, (star1, star2) -> star1))
                .values();
    }

    /**
     * Finds all stars.
     * This is a helper method for other methods.
     *
     * @return the list of all StarDto objects
     */
    @Override
    public List<StarDto> findAllStars() {
        List<Star> stars = starRepository.findAll();
        return stars.stream()
                .map(star -> objectMapper.convertValue(star, StarDto.class))
                .collect(Collectors.toList());
    }
}