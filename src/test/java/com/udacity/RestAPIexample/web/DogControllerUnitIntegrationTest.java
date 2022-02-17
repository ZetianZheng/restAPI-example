package com.udacity.RestAPIexample.web;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @RunWith:
 *      @RunWith(SpringRunner.class)，就是指用SpringRunner来运行，
 *
 * 先把服务器跑起来 ， 并用 SpringBootTest.WebEnvironment.RANDOM_PORT 放在随机端口，因为实测的时候会运行在多个port上：
 * @LocalServerPort 获取本地运行端口。
 *
 * restTemplate: [RestTemplate 最详解 - 程序员自由之路 - 博客园](https://www.cnblogs.com/54chensongxia/p/11414923.html)
 *      Http 常用客户端，spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，统一了RESTful的标准，封装了http链接,大大提高客户端的编写效率
 *      testRestTemplate can consume RestApi
 * getForEntity:
 *      retrieves a responseEntity by using Get
 *
 * @AutoConfigureMockMvc
 *      @AutoConfigureMockMvc是用于自动配置MockMvc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DogControllerUnitIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * integration test for getAllDogs Api，
     *      1. use RestTemplate as the Http client, get ResponseEntity for localhost:port/dogs;
     *          getForEntity(url, responseType);
 *          2. use assertThat to make it readable:
     *          assertThat([reason, ]T actual, Matcher<? super T> matcher)，
     *          其中，reason为断言失败时的输出信息，actual为断言的值或对象，
     *          matcher为断言的匹配器，里面的逻辑决定了给定的actual对象满不满足断言。
     */

    @Test
    public void getAllDogs() {
        ResponseEntity<List> response = this.restTemplate.getForEntity("http://localhost:" + port + "/dogs", List.class);

        MatcherAssert.assertThat(response.getStatusCode(), CoreMatchers.equalTo(HttpStatus.OK));
    }

}
