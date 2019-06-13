package kr.or.bit.chat.controller;

import kr.or.bit.chat.container.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
  private final SimpMessagingTemplate template;

  @Autowired
  public ChatController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @MessageMapping("/send/join")
  public void join(Message message) {
    message.setContent(message.getWriter() + "님이 입장");
    template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
  }

  @MessageMapping("/send/message")
  public void message(Message message) {
    template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
  }
}
