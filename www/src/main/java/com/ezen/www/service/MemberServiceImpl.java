package com.ezen.www.service;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public int insert(MemberVO mvo) {
        int isOk = memberMapper.insert(mvo);
        return (isOk > 0? memberMapper.insertAuth(mvo.getEmail()) : 0);
    }

    @Override
    public List<MemberVO> getAllList() {
        List<MemberVO> mlist = memberMapper.getAllList();
        for (MemberVO memberVO : mlist) {
            String email = memberVO.getEmail();
            List<AuthVO> authList = memberMapper.selectAuths(email);
            memberVO.setAuthList(authList);
        }
        return mlist;
    }

    @Override
    public void updateMember(MemberVO mvo) {
        memberMapper.updateMember(mvo);
    }

    @Override
    public void updatePW(MemberVO mvo) {
        memberMapper.updateMember(mvo);
    }

    @Override
    public void updateLastLogin(String email) {
        memberMapper.updateLastLogin(email);
    }

    @Override
    public void delete(String email) {
        memberMapper.deleteAuth(email);
        memberMapper.delete(email);
    }
}
