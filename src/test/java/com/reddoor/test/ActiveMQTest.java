package com.reddoor.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reddoor.tradesystem.activemq.SimpleProducer;

public class ActiveMQTest {
    @Test
    public void testAmqProducer()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-activemq.xml");
        SimpleProducer simpleProducer = (SimpleProducer) context.getBean("producer");
        try {
            simpleProducer.sendMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
