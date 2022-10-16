package consumers.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class MsgListener {

    @JmsListener(destination = "UserTest")
    public void receiveMessage(int id){
        System.out.println("已發送短信給 " + id + "號的使用者");

    }
}
