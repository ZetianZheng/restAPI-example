package com.udacity.RestAPIexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResponseStatus虽然只是规定了返回的状态，但是只需要标注在方法上，
 * 简单，而且状态码与返回类型分离，比较清晰。
 * @ResponseStatus 可以用于修饰一个类或者一个方法。修饰一个类的时候一般是一个异常类对类中所有方法起作用；当修饰一个方法时则是当该方法被触发且内部发生@ResponseStatus修饰异常时才起作用。
 * 一般在一个异常类在类上面加上@ResponseStatus注解，就表明在系统运行期间，当抛出这个用@ResponseStatus修饰的异常时，就会使用这个异常类@ResponseStatus注解上的 error code 和 error reasoon 返回给客户端，
 * 从而使异常信息以更友好的方式展示给用户，也提高可读性。
 * ref: https://blog.csdn.net/qq_20395245/article/details/106441369
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dog not found")
public class DogNotFoundException extends RuntimeException {

    public DogNotFoundException() {
    }

    public DogNotFoundException(String message) {
        super(message);
    }
}
