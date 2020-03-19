package com.reddoor.tradesystem.activemq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "simpleMsgListener")
public class SimpleMsgListener implements MessageListener {

    //收到信息时的动作
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("收到的信息：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

