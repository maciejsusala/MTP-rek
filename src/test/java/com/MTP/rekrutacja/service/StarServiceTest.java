package com.MTP.rekrutacja.service;

import com.MTP.rekrutacja.model.Star;
import com.MTP.rekrutacja.repository.StarRepository;
import com.MTP.rekrutacja.service.impl.StarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class StarServiceImplTest {

    @Mock
    private StarRepository starRepository;

    @InjectMocks
    private StarServiceImpl starServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findClosestStarsTest() {
        List<Star> stars = Arrays.asList(
                createStar("Alpha", 100),
                createStar("Beta", 50),
                createStar("Gamma", 20),
                createStar("Delta", 10),
                createStar("Epsilon", 5)
        );

        List<Star> expectedClosestStars = Arrays.asList(
                createStar("Epsilon", 5),
                createStar("Delta", 10),
                createStar("Gamma", 20)
        );

        List<Star> closestStars = starServiceImpl.findClosestStars(stars, 3);
        assertEquals(expectedClosestStars.size(), closestStars.size());
        for (int i = 0; i < expectedClosestStars.size(); i++) {
            assertEquals(expectedClosestStars.get(i).getName(), closestStars.get(i).getName());
            assertEquals(expectedClosestStars.get(i).getDistance(), closestStars.get(i).getDistance());
        }
    }

    @Test
    void getNumberOfStarsByDistancesTest() {
        List<Star> stars = Arrays.asList(
                createStar("Alpha", 100),
                createStar("Beta", 100),
                createStar("Gamma", 50),
                createStar("Delta", 50),
                createStar("Epsilon", 50)
        );

        Map<Long, Integer> expectedCount = new HashMap<>();
        expectedCount.put(100L, 2);
        expectedCount.put(50L, 3);

        Map<Long, Integer> starsByDistance = starServiceImpl.getNumberOfStarsByDistances(stars);
        assertEquals(expectedCount.size(), starsByDistance.size());
        expectedCount.forEach((distance, count) ->
                assertEquals(count, starsByDistance.get(distance))
        );
    }

    @Test
    void getUniqueStarsTest() {
        List<Star> stars = Arrays.asList(
                createStar("Alpha", 100),
                createStar("Alpha", 100),
                createStar("Beta", 50),
                createStar("Gamma", 50),
                createStar("Gamma", 50)
        );

        Set<Star> expectedUniqueStars = new HashSet<>(stars);

        Collection<Star> uniqueStars = starServiceImpl.getUniqueStars(stars);
        assertEquals(expectedUniqueStars.size(), uniqueStars.size());
    }

    private Star createStar(String name, long distance) {
        return Star.builder()
                .name(name)
                .distance(distance)
                .build();
    }
}