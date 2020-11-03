package tr.com.tai.mcs.tmgenerator.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.tai.mcs.tmgenerator.data.MessageBean;

import java.time.ZonedDateTime;

@Controller
public class SocketController {

    @RequestMapping("/volkan")
    public String start() {
        return "volkan";
    }

    @MessageMapping("/message")
    @SendTo("/topic/user")
    public MessageBean send(@Payload MessageBean message) {
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/output")
    public MessageBean chat(MessageBean input) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageBean output = new MessageBean();
        output.setMessage(input.getMessage());
        return output;
    }
}
