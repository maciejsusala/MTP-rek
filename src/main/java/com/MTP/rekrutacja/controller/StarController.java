package com.MTP.rekrutacja.controller;

import com.MTP.rekrutacja.dto.StarDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.MTP.rekrutacja.service.StarService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing stars.
 */

@RestController
@RequestMapping("/api/v1/stars")
@RequiredArgsConstructor
class StarController {

    private final StarService starService;

    /**
     * Finds a star by its ID.
     *
     * @param id the ID of the star
     * @return the found StarDto
     */
    @GetMapping("/{id}")
    public StarDto findStarById(@PathVariable Long id) {
        return starService.findById(id);
    }

    /**
     * Adds a new star.
     *
     * @param starDto the star data transfer object
     * @return the added StarDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StarDto addStar(@RequestBody @Valid StarDto starDto) {
        return starService.addStar(starDto);
    }

    /**
     * Updates an existing star.
     *
     * @param id the ID of the star to update
     * @param starDto the updated star data transfer object
     * @return the updated StarDto
     */
    @PutMapping("/{id}")
    public StarDto updateStar(@PathVariable Long id, @RequestBody @Valid StarDto starDto) {
        return starService.updateStar(id, starDto);
    }

    /**
     * Deletes a star by its ID.
     *
     * @param id the ID of the star to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStar(@PathVariable Long id) {
        starService.deleteStar(id);
    }

    /**
     * Finds the closest stars.
     *
     * @param size the number of closest stars to find
     * @return a list of the closest StarDto objects
     */
    @GetMapping("/closest")
    public List<StarDto> findClosestStars(@RequestParam int size) {
        List<StarDto> stars = starService.findAllStars();
        return starService.findClosestStars(stars, size);
    }

    /**
     * Gets the number of stars by their distances.
     *
     * @return a map where the key is the distance and the value is the number of stars at that distance
     */
    @GetMapping("/number-by-distances")
    public Map<Long, Integer> getNumberOfStarsByDistances() {
        List<StarDto> stars = starService.findAllStars();
        return starService.getNumberOfStarsByDistances(stars);
    }

    /**
     * Gets the unique stars.
     *
     * @return a collection of unique StarDto objects
     */
    @GetMapping("/unique")
    public Collection<StarDto> getUniqueStars() {
        Collection<StarDto> stars = starService.findAllStars();
        return starService.getUniqueStars(stars);
    }
}