package com.MTP.rekrutacja.repository;

import com.MTP.rekrutacja.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarRepository extends JpaRepository <Star, Long> {
    @Override
    Optional<Star> findById(Long id);
}
