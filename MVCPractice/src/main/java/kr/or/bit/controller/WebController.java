package kr.or.bit.controller;

import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @Autowired
  private SqlSession sqlSession;

  @GetMapping("/hello")
  public String hello(Model model) {
    MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
    Member member = memberDao.selectByUsername("bituser");
    model.addAttribute("name", member.getUsername());
    return "hello";
  }
}
