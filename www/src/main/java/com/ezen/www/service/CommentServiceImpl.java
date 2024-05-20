package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int post(CommentVO cvo) {
        return commentMapper.post(cvo);
    }

    @Override
    @Transactional
    public PagingHandler getList(long bno, PagingVO pgvo) {
        //totalCount
        int totalCount = commentMapper.bnoTotalCount(bno);
        //commentList
        List<CommentVO> cmtList = commentMapper.getList(bno, pgvo);

        return new PagingHandler(pgvo, totalCount, cmtList);
    }

    @Override
    public int modify(CommentVO cvo) {
       return commentMapper.modify(cvo);

    }

    @Override
    public int delete(int cno) {
        return commentMapper.delete(cno);
    }
}
