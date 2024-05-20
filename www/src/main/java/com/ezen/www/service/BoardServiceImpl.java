package com.ezen.www.service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardMapper;
import com.ezen.www.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileMapper fileMapper;

    @Transactional
    @Override
    public int insert(BoardDTO bdto) {
        int isOk = boardMapper.insert(bdto.getBvo());
        if(bdto.getFlist() == null) {
            return isOk;
        }
        if(isOk > 0 && bdto.getFlist().size()>0){
            long bno = boardMapper.getBno();
            for(FileVO fvo : bdto.getFlist()){
                fvo.setBno(bno);
                isOk *= fileMapper.insertFile(fvo);
            }
        }
        return isOk;
    }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {
        return boardMapper.getList(pgvo);
    }

    @Override
    public BoardDTO getDetail(long bno) {
        BoardVO bvo = boardMapper.getDetail(bno);
        List<FileVO>flist = fileMapper.getList(bno);
        BoardDTO bdto = new BoardDTO(bvo,flist);
        return bdto;
    }

    @Override
    public void modify(BoardVO bvo) {
        boardMapper.modify(bvo);
    }

    @Override
    public void remove(long bno) {
        boardMapper.remove(bno);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        return boardMapper.getTotalCount(pgvo);
    }

}
