package com.dziem.f1_personal_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Year year;
    Integer lapsQuantity;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Driver> driverList;
    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Weather> weatherList;
    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Result> resultList;
}
