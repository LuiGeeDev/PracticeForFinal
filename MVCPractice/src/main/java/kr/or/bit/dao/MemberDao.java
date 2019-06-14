package kr.or.bit.dao;

import kr.or.bit.dto.Member;

public interface MemberDao {
  Member selectByUsername(String username);
}
