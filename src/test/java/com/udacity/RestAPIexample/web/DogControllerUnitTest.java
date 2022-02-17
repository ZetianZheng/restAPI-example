package com.udacity.RestAPIexample.web;

import com.udacity.RestAPIexample.service.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * reference:: https://www.jianshu.com/p/13408dd4bef7
 *
 * mock dog service，不用真实的调用service。从而对controller do junit test
 *
 * @RunWith:
 *      @RunWith(SpringRunner.class)，就是指用SpringRunner来运行，
 *
 * @WebMcvTest:
 *      specific controller we want to test
 *
 * @MockBean:
 *      使用mock对象代替原来spring的bean，然后模拟底层数据的返回，而不是调用原本真正的实现。
 *
 * @MockMvc:
 *
 *
 * @Test:
 *      indicate that is an unit test.
 *
 * verify：
 *      检验Mock对象是否发生过某一个操作： dogService是否调用过 1次 retrieveDogs
 *
 * .perform() 执行一个MockMvcRequestBuilders请求。
 * 其中.get()表示发送get请求（可以使用get、post、put、delete等）；
 * .contentType()设置编码格式；
 * .param()请求参数,可以带多个。
 *
 *  andExpect()添加 MockMvcResultMatchers验证规则，验证执行结果是否正确。
 *
 * .andDo()添加 MockMvcResultHandlers结果处理器,这是可以用于打印结果输出。
 *
 * .andReturn()结果返回，然后可以进行下一步的处理。
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DogController.class)
public class DogControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DogService dogService;

    @Test
    public void getAllDogs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dogs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[]"))
                .andDo(MockMvcResultHandlers.print());


        Mockito.verify(dogService, VerificationModeFactory.times(1)).retrieveDogs();
    }

    @Test
    public void getBreedByID() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("1/breed")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
