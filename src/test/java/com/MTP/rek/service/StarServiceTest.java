package com.MTP.rek.service;

import com.MTP.rek.model.Star;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class StarServiceTest {

    private final StarService starService = new StarService();

    @Test
    void findClosestStarsTest() {
        List<Star> stars = Arrays.asList(
                new Star("Alpha", 100),
                new Star("Beta", 50),
                new Star("Gamma", 20),
                new Star("Delta", 10),
                new Star("Epsilon", 5)
        );
        List<Star> closestStars = starService.findClosestStars(stars, 3);
        assertEquals(3, closestStars.size());
        assertEquals("Epsilon", closestStars.get(0).getName());
        assertEquals("Delta", closestStars.get(1).getName());
        assertEquals("Gamma", closestStars.get(2).getName());
    }

    @Test
    void getNumberOfStarsByDistancesTest() {
        List<Star> stars = Arrays.asList(
                new Star("Alpha", 100),
                new Star("Beta", 100),
                new Star("Gamma", 50),
                new Star("Delta", 50),
                new Star("Epsilon", 50)
        );
        Map<Long, Integer> starsByDistance = starService.getNumberOfStarsByDistances(stars);
        assertEquals(2, starsByDistance.size());
        assertEquals(Integer.valueOf(2), starsByDistance.get(100L));
        assertEquals(Integer.valueOf(3), starsByDistance.get(50L));
    }

    @Test
    void getUniqueStarsTest() {
        List<Star> stars = Arrays.asList(
                new Star("Alpha", 100),
                new Star("Alpha", 100),
                new Star("Beta", 50),
                new Star("Gamma", 50),
                new Star("Gamma", 50)
        );
        Collection<Star> uniqueStars = starService.getUniqueStars(stars);
        assertEquals(3, uniqueStars.size());
    }
}