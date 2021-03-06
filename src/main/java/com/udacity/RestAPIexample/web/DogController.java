package com.udacity.RestAPIexample.web;

import com.udacity.RestAPIexample.entity.Dog;
import com.udacity.RestAPIexample.service.DogService;
import io.swagger.annotations.*;
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
 *
 * @ApiResponses:
 *      自定义code status message
 *
 * @ApiOperation:
 *      Describes an operation or typically a HTTP method against a specific path.
 *
 * @ApiImplicitParam
 *      非对象参数描述
 * [SWAGGER注释API详细说明 - 星朝 - 博客园](https://www.cnblogs.com/jpfss/p/12120423.html)
 */
@RestController
@ApiResponses(value = {
        @ApiResponse(code=400, message = "This is a bad request"),
        @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized"),
        @ApiResponse(code=500, message = "the server down")
})
@Api(value="DogController to handle request for dogs checking")
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
    @ApiOperation(value ="get all dogs list")
    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogs = dogService.retrieveDogs();
        return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);
    }

    @GetMapping("/dogs/breed")
    @ApiOperation(value ="get all dogs breeds list")
    public ResponseEntity<List<String>> getDogBreeds() {
        List<String> list = dogService.retrieveDogBreeds();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    @ApiOperation(value ="get dog breed by Id", notes="need dog id")
    @ApiImplicitParam(paramType = "query", name="id", value="dog id",required = true, dataType = "Long")
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
