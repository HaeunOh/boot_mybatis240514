package com.ezen.www.repository;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {

    List<BoardVO> getList(PagingVO pgvo);

    int insert(BoardVO bvo);

    BoardVO getDetail(long bno);

    void modify(BoardVO bvo);

    void remove(long bno);

    int getTotalCount(PagingVO pgvo);

    long getBno();
}
