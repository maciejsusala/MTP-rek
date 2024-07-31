package com.MTP.rekrutacja.controller;

import com.MTP.rekrutacja.dto.StarDto;
import com.MTP.rekrutacja.model.Star;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.MTP.rekrutacja.service.StarService;

import java.util.Collection;
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

    @GetMapping("/closest")
    public List<StarDto> findClosestStars(@RequestParam int size) {
        List<StarDto> stars = starService.findAllStars();
        return starService.findClosestStars(stars, size);
    }

    @GetMapping("/number-by-distances")
    public Map<Long, Integer> getNumberOfStarsByDistances() {
        List<StarDto> stars = starService.findAllStars();
        return starService.getNumberOfStarsByDistances(stars);
    }

    @GetMapping("/unique")
    public Collection<Star> getUniqueStars() {
        Collection<StarDto> stars = starService.findAllStars();
        return starService.getUniqueStars(stars);
    }
}