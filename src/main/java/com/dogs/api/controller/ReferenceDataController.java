package com.dogs.api.controller;

import com.dogs.api.dto.DogStatus;
import com.dogs.api.dto.Gender;
import com.dogs.api.dto.LeavingReason;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dogs")
public class ReferenceDataController {

    @GetMapping("/statuses")
    public DogStatus[] statuses() {
        return DogStatus.values();
    }

    @GetMapping("/genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @GetMapping("/leaving-reasons")
    public LeavingReason[] leavingReasons() {
        return LeavingReason.values();
    }
}
