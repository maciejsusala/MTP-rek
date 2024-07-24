package com.MTP.rek.service;

import com.MTP.rek.model.Star;

import java.util.*;
import java.util.stream.Collectors;


public class StarService {

    public List<Star> findClosestStars(List<Star> stars, int size) {
        return stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        return stars.stream()
                .collect(Collectors.groupingBy(Star::getDistance, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        return stars.stream()
                .collect(Collectors.toMap(Star::getName, star -> star, (star1, star2) -> star1))
                .values();
    }
}