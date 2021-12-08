package com.dyKim.domain.board;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dyKim.domain.board.mapper.BoardMapper;

public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1) ? true : false;
	}

	
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		if(board != null ) {
			if("N".equals(board.getDeleteYn())) {
				queryResult = boardMapper.deleteBoard(idx);
			}
		}
		
		return (queryResult == 1) ? true : false;
	}

	
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.selectBoardCount();
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
	
}
