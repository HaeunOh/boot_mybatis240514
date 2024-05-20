package com.ezen.www.service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    int insert(BoardDTO bdto);

    List<BoardVO> getList(PagingVO pgvo);

    BoardDTO getDetail(long bno);

    void modify(BoardVO bvo);

    void remove(long bno);

    int getTotalCount(PagingVO pgvo);
}
