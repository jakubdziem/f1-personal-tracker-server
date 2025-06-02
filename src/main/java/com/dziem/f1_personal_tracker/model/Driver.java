package com.dziem.f1_personal_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Race> raceList;
    @OneToMany
    List<Result> resultList;
}
