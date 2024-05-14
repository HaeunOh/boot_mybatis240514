package com.ezen.www.service;

import com.ezen.www.domain.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    void insert(BoardVO bvo);

    List<BoardVO> getList();

    BoardVO getDetail(long bno);

    void modify(BoardVO bvo);

    void remove(long bno);
}
