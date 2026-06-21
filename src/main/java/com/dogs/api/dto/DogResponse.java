package com.dogs.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DogResponse {

    private Long id;

    private String name;

    private String breed;

    private String supplier;

    private String badgeId;

    private Gender gender;

    private LocalDate birthDate;

    private LocalDate dateAcquired;

    private DogStatus currentStatus;

    private LocalDate leavingDate;

    private LeavingReason leavingReason;

    private String kennellingCharacteristic;
}