package com.dziem.f1_personal_tracker.model;

import com.dziem.f1_personal_tracker.controller.Race;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Race> raceList;
}
