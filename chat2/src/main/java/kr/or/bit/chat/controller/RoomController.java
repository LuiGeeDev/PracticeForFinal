package kr.or.bit.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {
  @GetMapping("/room")
  public String showRoom(String id) {
    return "room";
  }
}
