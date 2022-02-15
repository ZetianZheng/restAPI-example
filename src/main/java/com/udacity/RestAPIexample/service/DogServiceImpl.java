package com.udacity.RestAPIexample.service;

import com.udacity.RestAPIexample.entity.Dog;
import com.udacity.RestAPIexample.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DogService 的实现，方便调用
 * @Autowired:
 *  Spring 2.5 引入了 @Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，
 *  完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
 * @repository.findAll():
 */
@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    public List<Dog> retrieveDogs() {
        return (List<Dog>)dogRepository.findAll();
    }

    public List<String> retrieveDogBreeds() {
        return dogRepository.findAllBreed();
    }

    public String retrieveDogBreedById(Long id) {
        return dogRepository.findBreedById(id);
    }

    public List<String> retrieveDogNames() {
        return dogRepository.findAllName();
    }
}
