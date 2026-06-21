package com.dogs.api.repository;

import com.dogs.api.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DogRepository extends JpaRepository<Dog, Long>, JpaSpecificationExecutor<Dog> {
}

