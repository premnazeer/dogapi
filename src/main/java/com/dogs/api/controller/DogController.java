package com.dogs.api.controller;

import com.dogs.api.dto.DogRequest;
import com.dogs.api.dto.DogResponse;
import com.dogs.api.entity.Dog;
import com.dogs.api.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService service;
    private final ObjectMapper objectMapper;

    @PostMapping("/dogs")
    public ResponseEntity<DogResponse> create(@Valid @RequestBody DogRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/dogs")
    public Page<Dog> getDogs(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String supplier,
            Pageable pageable
    ) {
        return service.getDogs(name, breed, supplier, pageable);
    }

    @GetMapping("/dogs/{id}")
    public DogResponse getDog(@PathVariable Long id) {
        return service.getDog(id);
    }

    @PutMapping("/dogs/{id}")
    public DogResponse update(@PathVariable Long id, @RequestBody DogRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/dogs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}