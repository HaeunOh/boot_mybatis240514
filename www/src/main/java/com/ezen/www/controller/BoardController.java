package com.ezen.www.controller;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService bsv;

    @Autowired
    private FileHandler fh;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String register(BoardVO bvo, @RequestParam(name="files", required = false) MultipartFile[] files){
        List<FileVO> flist= null;
        if(files[0].getSize() > 0) {
            flist = fh.uploadFiles(files);
        }
        BoardDTO bdto = new BoardDTO(bvo, flist);
        log.info("bdto: 값 {}", bdto);
        int isOk = bsv.insert(bdto);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model m, PagingVO pgvo) {
        log.info(">>>> pgvo >>> {}", pgvo);
        //totalCount DB에서 가져오기
        int totalCount = bsv.getTotalCount(pgvo);

        //PagingHandler 객체 생성
        PagingHandler ph = new PagingHandler(pgvo, totalCount);
        List<BoardVO> list = bsv.getList(pgvo);
        log.info("게시글 목록 >>>> {} >>", list);
        m.addAttribute("list", list);
        m.addAttribute("ph", ph);
    }

    @GetMapping("/detail")
    public void detail(Model m, @RequestParam("bno") long bno){
        BoardDTO bdto = bsv.getDetail(bno);
        log.info("bdto 체크 {}",bdto);
        m.addAttribute("bdto", bdto);
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
