package com.dyKim;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dyKim.domain.board.BoardDTO;
import com.dyKim.domain.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	
	@Test
	public void testOfInsert() {
		 BoardDTO params = new BoardDTO();
		 params.setTitle("1번 게시글 제목");
		 params.setContent("1번 게시글 내용");
		 params.setWriter("테스터");
		 
		 int result = boardMapper.insertBoard(params);
		 System.out.println("결과 : " +  result);
	}
	
	@Test
	public void testSelectBoardList() {
		System.out.println(boardMapper.selectBoardList());
	}
	
	@Test
	public void testSelectBoardDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long)2);
		try {
			String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
			System.out.println(boardJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testUpdateBoard() {
		BoardDTO params = new BoardDTO();
		params.setTitle("11111");
		params.setContent("121121212");
		params.setWriter("홍길동ggg");
		params.setIdx((long)2);
		
		int result = boardMapper.updateBoard(params);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				System.out.println(boardJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testDeleteBoard() {
		BoardDTO params = new BoardDTO();
		int result = boardMapper.deleteBoard((long)2);
		if(result == 1) {
			System.out.println("성공적으로 지워졌습니다.");
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				System.out.println(boardJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("삭제에 실패했습니다.");
		}
	}
	
	@Test
	public void testMultipleInsert() {
		for(int i=0; i<50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i+"번 게시글 제목");
			params.setContent(i+"번 게시글 내용");
			params.setWriter(i+"번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	
}
