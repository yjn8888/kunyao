package com.kunyao.test;

import com.kunyao.StartupApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebApplicationTest extends GenericApplicationTest{

    @Autowired
    protected WebTestClient webClient;

    @Autowired
    protected WebApplicationContext webApplicationContext;


    /**
     *  1.mockMvc.perform执行一个请求
     *  2.MockMvcRequestBuilders.get(“/user/1”)构造一个请求，Post请求就用.post方法
     *  3.contentType(MediaType.APPLICATION_JSON_UTF8)代表发送端发送的数据格式是application/json;charset=UTF-8
     *  4.accept(MediaType.APPLICATION_JSON_UTF8)代表客户端希望接受的数据类型为application/json;charset=UTF-8
     *  5.session(session)注入一个session，这样拦截器才可以通过
     *  6.ResultActions.andExpect添加执行完成后的断言
     *  7.ResultActions.andExpect(MockMvcResultMatchers.status().isOk())方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
     *  8.andExpect(MockMvcResultMatchers.jsonPath(“$.author”).value(“嘟嘟MD独立博客”))这里jsonPath用来获取author字段比对是否为嘟嘟MD独立博客,不是就测试不通过
     *  9.ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
     */
    protected MockMvc mockMvc;

    /**
     * @Author The little blacksmith
     * @Description 启动前执行
     * @Date 2020/3/31
     * @Param []
     * @return void
     */
    @Before
    @Override
    public void before() {
        super.before();
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }
}
