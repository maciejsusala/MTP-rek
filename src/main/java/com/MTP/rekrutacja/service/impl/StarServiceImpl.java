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

@Service
@Slf4j
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;
    private final ObjectMapper objectMapper;


    @Override
    public StarDto findById(Long id) {
        Star starEntity = starRepository.findById(id)
                .orElseThrow(() -> new StarNotFoundException("Star not found with id " + id));

        return objectMapper.convertValue(starEntity, StarDto.class);
    }

    @Override
    @Transactional
    public StarDto addStar(StarDto starDto) {
        log.info("Adding new star with details {}", starDto);
        Star star = objectMapper.convertValue(starDto, Star.class);
        Star savedStar = starRepository.save(star);
        return objectMapper.convertValue(savedStar, StarDto.class);
    }

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

    @Override
    @Transactional
    public void deleteStar(Long id) {
        log.info("Deleting star with id {}", id);
        Star star = starRepository.findById(id)
                .orElseThrow(() -> new StarNotFoundException("Star not found with id " + id));
        starRepository.delete(star);
    }

    @Override
    public List<StarDto> findAllStars() {
        List<Star> stars = starRepository.findAll();
        return stars.stream()
                .map(star -> objectMapper.convertValue(star, StarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StarDto> findClosestStars(List<StarDto> stars, int size) {
        return stars.stream()
                .sorted(Comparator.comparingLong(StarDto::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    //TODO change to StarDTO
    @Override
    public Map<Long,Integer> getNumberOfStarsByDistances(List<StarDto> stars) {
        return stars.stream()
                .collect(Collectors.groupingBy(
                        StarDto::getDistance,
                        TreeMap::new,
                        Collectors.summingInt(star -> 1)
                ));
    }

    //TODO change to StarDTO
    @Override
    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        return stars.stream()
                .collect(Collectors.toMap(Star::getName, star -> star, (star1, star2) -> star1))
                .values();
    }
}