package com.ssafy.enjoytrip.board.model.service;

import java.util.List;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.BoardPageBean;

public interface BoardService {

	void writeArticle(Board boardDto);
	List<Board> listArticle(BoardPageBean bean);
	Board getArticle(int boardId);
	void updateHit(int boardId);
	void modifyArticle(Board boardDto);
	void deleteArticle(int boardId);
	Comment getComment(int commentId);
	void writeComment(Comment comment);
	void modifyComment(Comment comment);
	void deleteComment(int commentId);
	List<Comment> listComment(int boardId);

}
