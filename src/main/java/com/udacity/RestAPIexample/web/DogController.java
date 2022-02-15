package com.udacity.RestAPIexample.web;

import com.udacity.RestAPIexample.entity.Dog;
import com.udacity.RestAPIexample.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @RestController:
 *      @RestController is a specialized version of the controller.
 *      It includes the @Controller and @ResponseBody annotations,
 *      and as a result, simplifies the controller implementation:
 *      The controller is annotated with the @RestController annotation;
 *      therefore, the @ResponseBody isn't required.
 *      Every request handling method of the controller class automatically serializes return objects into HttpResponse.
 *
 * @Autowired
 *      寻找入参 DogService的bean 并将它注入到构造函数中。
 *
 * @ResponseEntity<List<Dog>>:
 *  　　ResponseEntity 表示整个HTTP响应：状态代码，标题和正文。因此，我们可以使用它来完全配置HTTP响应
 *
 * @PathVariable
 *      @pathVariable 是直接从uri的占位符中extract 值。URL 中的 {xxx} 占位符可以通过@PathVariable(“xxx“) 绑定到操作方法的入参中。
 */
@RestController
public class DogController {
    private DogService dogService;


    @Autowired
    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * body 是dogs， 状态码是HttpStatus.OK
     * @return
     */
    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogs = dogService.retrieveDogs();
        return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);
    }

    @GetMapping("/dogs/breed")
    public ResponseEntity<List<String>> getDogBreeds() {
        List<String> list = dogService.retrieveDogBreeds();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedByID(@PathVariable Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<String>(breed, HttpStatus.OK);
    }

    @GetMapping("/dogs/name")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> list = dogService.retrieveDogNames();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }
}
