package com.ezen.www.controller;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/member/*")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService msv;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(MemberVO mvo){
        mvo.setPassword(passwordEncoder.encode(mvo.getPassword()));
        int isOk = msv.insert(mvo);
        return "index";
    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/list")
    public void list(Model m){
        List<MemberVO> mlist = msv.getAllList();
        m.addAttribute("list", mlist);
    }
    @GetMapping("/modify")
    public void modify() {}

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request,response,authentication);
    }
    @PostMapping("/modify")
    public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
        String password = mvo.getPassword();
        if(password.isEmpty()) {
            msv.updateMember(mvo);
        }else {
            mvo.setPassword(passwordEncoder.encode(mvo.getPassword()));
            msv.updatePW(mvo);
        }
        msv.updateLastLogin(mvo.getEmail());
        logout(request, response);
        return "redirect:/";
    }
    @GetMapping("/remove")
    public String remove(@RequestParam("email")String email) {
        log.info("email확인 {}",email);
        msv.delete(email);
        return "redirect:/member/logout";
    }
}
