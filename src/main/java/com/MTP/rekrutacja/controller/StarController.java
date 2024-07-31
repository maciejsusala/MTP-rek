package com.MTP.rekrutacja.controller;

import com.MTP.rekrutacja.dto.StarDto;
import com.MTP.rekrutacja.model.Star;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.MTP.rekrutacja.service.StarService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stars")
@RequiredArgsConstructor
class StarController {

    private final StarService starService;

    @GetMapping("/{id}")
    public StarDto findStarById(@PathVariable Long id) {
        return starService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StarDto addStar(@RequestBody @Valid StarDto starDto) {
        return starService.addStar(starDto);
    }

    @PutMapping("/{id}")
    public StarDto updateStar(@PathVariable Long id, @RequestBody @Valid StarDto starDto) {
        return starService.updateStar(id, starDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStar(@PathVariable Long id) {
        starService.deleteStar(id);
    }

    //TODO add endpoints for 3 services
    @GetMapping("/closest")
    public List<Star> findClosestStars(@RequestParam int size) {
        List<Star> stars = starService.findAllStars();
        return starService.findClosestStars(stars, size);
    }

    @GetMapping("/number-by-distances")
    public Map<Long, Integer> getNumberOfStarsByDistances() {
        List<Star> stars = starService.findAllStars();
        return starService.getNumberOfStarsByDistances(stars);
    }


    //Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars);
    //Collection<Star> getUniqueStars(Collection<Star> stars);
}