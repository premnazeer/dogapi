package com.dogs.api.entity;

import com.dogs.api.dto.DogStatus;
import com.dogs.api.dto.Gender;
import com.dogs.api.dto.LeavingReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@Table(name = "dogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String breed;

    private String supplier;

    private String badgeId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    private LocalDate dateAcquired;

    @Enumerated(EnumType.STRING)
    private DogStatus currentStatus;

    private LocalDate leavingDate;

    @Enumerated(EnumType.STRING)
    private LeavingReason leavingReason;

    private String kennellingCharacteristic;

    @Column(nullable = false)
    private boolean deleted = false;
}