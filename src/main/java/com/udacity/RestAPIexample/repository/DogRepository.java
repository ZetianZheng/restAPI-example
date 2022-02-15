package com.udacity.RestAPIexample.repository;


import com.udacity.RestAPIexample.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * While Spring implements a lot of the repository for you,
 * I have added a few helpful queries to be able to obtain some of the necessary information for our DogService later.
 * JPQL grammar
 * JPQL可以操作实体模型 并通过属性的方法 指定返回对应列 d.id, d.breed 返回两个列
 * :id 绑定 id 参数
 */
public interface DogRepository extends CrudRepository<Dog,Long> {
    @Query("select d.id, d.breed from Dog d where d.id=:id")
    String findBreedById(Long id);

    @Query("select d.id, d.breed from Dog d")
    List<String> findAllBreed();

    @Query("select d.id, d.name from Dog d")
    List<String> findAllName();
}
