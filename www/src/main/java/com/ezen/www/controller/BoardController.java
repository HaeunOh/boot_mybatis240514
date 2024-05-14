package com.ezen.www.controller;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService bsv;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(BoardVO bvo){
        log.info("게시글 등록 >>>> {} >>", bvo);
        bsv.insert(bvo);
        return "/index";
    }

    @GetMapping("/list")
    public void list(Model m) {
        List<BoardVO> list = bsv.getList();
        log.info("게시글 목록 >>>> {} >>", list);
        m.addAttribute("list", list);
    }

    @GetMapping("/detail")
    public void detail(Model m, @RequestParam("bno") long bno){
        m.addAttribute("bvo", bsv.getDetail(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO bvo){
        bsv.modify(bvo);
        return "redirect:/board/detail?bno="+bvo.getBno();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") long bno){
        bsv.remove(bno);
        return "redirect:/board/list";
    }
}
