package com.MTP.rekrutacja.repository;

import com.MTP.rekrutacja.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Star entities.
 */
public interface StarRepository extends JpaRepository <Star, Long> {

    /**
     * Repository interface for managing Star entities.
     */
    @Override
    Optional<Star> findById(Long id);
}
