package com.MTP.rekrutacja.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Star {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "distance", nullable = false)
    private long distance;
}

