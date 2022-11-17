package com.board.model;

import java.util.ArrayList;

public interface SBoardDAO {
	// 추가
	public void boardInsert(BoardDTO board);
	
	// 수정
	public void boardUpdate(BoardDTO board);
	
	// 전체보기
	public ArrayList<BoardDTO> boardList();
	public ArrayList<BoardDTO> boardList(int startRow, int endRow, String field, String word);
	
	// 삭제
	public void boardDelete(int num);
	
	// 게시글 수
	public int boardCount(String field, String word);
	
	// 상세보기
	public BoardDTO findByNum(int num);
	
	// comment
	
	// comment 추가
	public void commentInsert(CommentDTO comment);
	
	// comment 전체보기
	public ArrayList<CommentDTO> findAllComment(int num);
	
	// comment 댓글 개수
	public int commentCount(int bnum);
	
	// comment 게시글 수
		
	// comment 상세보기

}
