package com.MTP.rek.service;

import com.MTP.rek.model.Star;

import java.util.*;
import java.util.stream.Collectors;


public class StarService {

    public List<Star> findClosestStars(List<Star> stars, int size) {
        stars.sort(Comparator.comparingLong(Star::getDistance));
        return new ArrayList<>(stars.subList(0, Math.min(size, stars.size())));
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        Map<Long, Integer> starsByDistance = new TreeMap<>();
        for (Star star : stars) {
            long distance = star.getDistance();
            starsByDistance.put(distance, starsByDistance.getOrDefault(distance, 0) + 1);
        }
        return starsByDistance;
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        Map<String, Star> uniqueStars = new HashMap<>();
        for (Star star : stars) {
            if (!uniqueStars.containsKey(star.getName())) {
                uniqueStars.put(star.getName(), star);
            }
        }
        return uniqueStars.values();
    }

    //Uwagi - wiem że możnaby zastosować programowanie funkcyjne,
    // ale nie jestem pewien czy to jest wymagane
}