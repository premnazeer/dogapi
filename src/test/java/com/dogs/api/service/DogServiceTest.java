package com.dogs.api.service;

import com.dogs.api.dto.DogRequest;
import com.dogs.api.dto.DogResponse;
import com.dogs.api.entity.Dog;
import com.dogs.api.mapper.DogMapper;
import com.dogs.api.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository repository;

    @Mock
    private DogMapper mapper;

    @InjectMocks
    private DogService service;

    private Dog dog;
    private DogRequest dogRequest;
    private DogResponse dogResponse;

    @BeforeEach
    void setup() {

        dog = new Dog();
        dog.setId(1L);
        dog.setName("Rex");

        dogRequest = new DogRequest();
        dogRequest.setName("Rex");

        dogResponse = new DogResponse();
        dogResponse.setId(1L);
        dogResponse.setName("Rex");
    }

    @Test
    void shouldCreateDog() {

        when(mapper.toEntity(dogRequest)).thenReturn(dog);
        when(repository.save(dog)).thenReturn(dog);
        when(mapper.toResponse(dog)).thenReturn(dogResponse);

        DogResponse result = service.create(dogRequest);

        assertThat(result.getName()).isEqualTo("Rex");

        verify(repository).save(dog);
    }

    @Test
    void shouldGetDogById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(dog));

        when(mapper.toResponse(dog))
                .thenReturn(dogResponse);

        DogResponse result = service.getDog(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Rex");
    }

    @Test
    void shouldSoftDeleteDog() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(dog));

        service.delete(1L);

        assertThat(dog.isDeleted()).isTrue();

        verify(repository).save(dog);
    }

    @Test
    void shouldUpdateDog() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(dog));

        when(repository.save(dog))
                .thenReturn(dog);

        when(mapper.toResponse(dog))
                .thenReturn(dogResponse);

        DogResponse response =
                service.update(1L, dogRequest);

        verify(mapper)
                .update(dogRequest, dog);

        assertThat(response).isNotNull();
    }
}