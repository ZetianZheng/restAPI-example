package com.udacity.RestAPIexample.service;

import com.udacity.RestAPIexample.entity.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DogService {
    List<Dog> retrieveDogs();
    List<String> retrieveDogBreeds();
    String retrieveDogBreedById(Long id);
    List<String> retrieveDogNames();
}
