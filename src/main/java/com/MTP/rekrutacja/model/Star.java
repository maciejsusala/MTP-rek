package com.MTP.rekrutacja.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a star.
 */
@Entity
@Table(name = "stars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Star {
    /**
     * The ID of the star.
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The name of the star.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The distance of the star.
     */
    @Column(name = "distance", nullable = false)
    private long distance;
}

