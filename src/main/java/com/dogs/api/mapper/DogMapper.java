package com.dogs.api.mapper;

import com.dogs.api.dto.DogRequest;
import com.dogs.api.dto.DogResponse;
import com.dogs.api.entity.Dog;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {

    public DogResponse toResponse(Dog entity) {
        DogResponse dto = new DogResponse();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBreed(entity.getBreed());
        dto.setSupplier(entity.getSupplier());
        dto.setBadgeId(entity.getBadgeId());
        dto.setGender(entity.getGender());
        dto.setBirthDate(entity.getBirthDate());
        dto.setDateAcquired(entity.getDateAcquired());
        dto.setCurrentStatus(entity.getCurrentStatus());
        dto.setLeavingDate(entity.getLeavingDate());
        dto.setLeavingReason(entity.getLeavingReason());
        dto.setKennellingCharacteristic(entity.getKennellingCharacteristic());

        return dto;
    }

    public Dog toEntity(DogRequest request) {
        Dog entity = new Dog();

        update(request, entity);

        return entity;
    }

    public void update(DogRequest request, Dog entity) {

        entity.setName(request.getName());
        entity.setBreed(request.getBreed());
        entity.setSupplier(request.getSupplier());
        entity.setBadgeId(request.getBadgeId());
        entity.setGender(request.getGender());
        entity.setBirthDate(request.getBirthDate());
        entity.setDateAcquired(request.getDateAcquired());
        entity.setCurrentStatus(request.getCurrentStatus());
        entity.setLeavingDate(request.getLeavingDate());
        entity.setLeavingReason(request.getLeavingReason());
        entity.setKennellingCharacteristic(
                request.getKennellingCharacteristic());
    }
}