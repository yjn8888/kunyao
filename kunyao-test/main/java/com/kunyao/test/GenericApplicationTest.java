package com.kunyao.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public abstract class GenericApplicationTest implements ApplicaionTest{

    @Before
    @Override
    public void before() {
        log.info("单元测试开始》》》》》》》》》》》》》》》》》》》》》》》》》》》");
    }

    @After
    @Override
    public void after() {
        log.info("单元测试结束《《《《《《《《《《《《《《《《《《《《《《《《《《《");
    }
}
