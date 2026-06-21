package com.dogs.api.specification;

import com.dogs.api.entity.Dog;
import org.springframework.data.jpa.domain.Specification;

public class DogSpecification {


    public static Specification<Dog> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Dog> hasBreed(String breed) {
        return (root, query, cb) -> {
            if (breed == null || breed.isBlank()) return null;
            return cb.equal(root.get("breed"), breed);
        };
    }

    public static Specification<Dog> hasSupplier(String supplier) {
        return (root, query, cb) -> {
            if (supplier == null || supplier.isBlank()) return null;
            return cb.equal(root.get("supplier"), supplier);
        };
    }

    public static Specification<Dog> notDeleted() {
        return (root, query, cb) -> cb.isFalse(root.get("deleted"));
    }
}
