package com.MTP.rekrutacja.service;

import com.MTP.rekrutacja.dto.StarDto;
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
        List<StarDto> stars = Arrays.asList(
                createStarDto("Alpha", 100),
                createStarDto("Beta", 50),
                createStarDto("Gamma", 20),
                createStarDto("Delta", 10),
                createStarDto("Epsilon", 5)
        );

        List<StarDto> expectedClosestStars = Arrays.asList(
                createStarDto("Epsilon", 5),
                createStarDto("Delta", 10),
                createStarDto("Gamma", 20)
        );

        List<StarDto> closestStars = starServiceImpl.findClosestStars(stars, 3);
        assertEquals(expectedClosestStars.size(), closestStars.size());
        for (int i = 0; i < expectedClosestStars.size(); i++) {
            assertEquals(expectedClosestStars.get(i).getName(), closestStars.get(i).getName());
            assertEquals(expectedClosestStars.get(i).getDistance(), closestStars.get(i).getDistance());
        }
    }

    @Test
    void getNumberOfStarsByDistancesTest() {
        List<StarDto> stars = Arrays.asList(
                createStarDto("Alpha", 100),
                createStarDto("Beta", 100),
                createStarDto("Gamma", 50),
                createStarDto("Delta", 50),
                createStarDto("Epsilon", 50)
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
        List<StarDto> stars = Arrays.asList(
                createStarDto("Alpha", 100),
                createStarDto("Alpha", 100),
                createStarDto("Beta", 50),
                createStarDto("Gamma", 50),
                createStarDto("Gamma", 50)
        );

        Set<StarDto> expectedUniqueStars = new HashSet<>(stars);

        Collection<StarDto> uniqueStars = starServiceImpl.getUniqueStars(stars);
        assertEquals(expectedUniqueStars.size(), uniqueStars.size());
    }

    private StarDto createStarDto(String name, long distance) {
        return StarDto.builder()
                .name(name)
                .distance(distance)
                .build();
    }
}