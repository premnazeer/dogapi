package com.dogs.api.service;

import com.dogs.api.dto.DogRequest;
import com.dogs.api.dto.DogResponse;
import com.dogs.api.entity.Dog;
import com.dogs.api.mapper.DogMapper;
import com.dogs.api.repository.DogRepository;
import com.dogs.api.specification.DogSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DogService {

    private final DogRepository repository;
    private final DogMapper mapper;

    public DogResponse create(DogRequest request) {

        Dog entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public DogResponse getDog(Long id) {

        Dog entity = repository.findById(id).filter(d -> !d.isDeleted()).orElseThrow();
        return mapper.toResponse(entity);
    }

    public Page<Dog> getDogs(String name, String breed, String supplier, Pageable pageable) {

        Specification<Dog> spec = DogSpecification.hasName(name)
                        .and(DogSpecification.hasBreed(breed))
                        .and(DogSpecification.hasSupplier(supplier))
                        .and(DogSpecification.notDeleted());

        return repository.findAll(spec, pageable);
    }

    public DogResponse update(Long id, DogRequest request) {

        Dog entity = repository.findById(id).orElseThrow();
        mapper.update(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    public void delete(Long id) {

        Dog entity = repository.findById(id).orElseThrow();
        entity.setDeleted(true);
        repository.save(entity);
    }

}
