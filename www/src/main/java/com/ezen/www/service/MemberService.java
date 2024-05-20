package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

import java.util.List;

public interface MemberService {

    int insert(MemberVO mvo);

    List<MemberVO> getAllList();

    void updateMember(MemberVO mvo);

    void updatePW(MemberVO mvo);

    void updateLastLogin(String email);

    void delete(String email);
}
