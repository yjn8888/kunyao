package com.kunyao.message.rabbitmq;

import com.kunyao.test.GenericApplicationTest;
import com.kunyao.StartupApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author The little blacksmith
 * @Description TODO
 * @Date 2020/3/30 15:20
 * @Version 1.0
 */
public class RabbitMqTestApplication extends GenericApplicationTest {

    @Autowired
    private RabbitMQProducerTest rabbitMQProducerTest;

    @Test
    public void sendMessageTest(){
        int i = 0;
        for(;;){
            //TimeUnit.MILLISECONDS.sleep(10);
            rabbitMQProducerTest.sendMessage(new TestMessage(i,"消息"+i,i+10,"北京"));
            i++;
            if(i==100){
                break;
            }
        }
    }

}
