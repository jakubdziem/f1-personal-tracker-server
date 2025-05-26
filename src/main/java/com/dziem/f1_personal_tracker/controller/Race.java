package com.dziem.f1_personal_tracker.controller;

import com.dziem.f1_personal_tracker.model.Driver;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer lapsQuantity;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Driver> driverList;
    @OneToMany
    List<Weather> weatherList;
}
