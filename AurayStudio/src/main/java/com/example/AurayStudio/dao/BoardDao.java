package com.example.AurayStudio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import com.example.AurayStudio.dto.BoardDto;
import com.example.AurayStudio.dto.PostDto;
import com.example.AurayStudio.dto.ReplyDto;


@Mapper
public interface BoardDao {
	@Select("select * from post order by post_no desc")
	public List<PostDto> selectPostListAll() throws DataAccessException;
	
	@Select("select * from post where board_no=#{board_no} order by post_no desc limit #{offset}, #{cnt}")
	public List<PostDto> selectPostListByBoardNo(@Param("board_no") int board_no,
			@Param("offset") int offset, @Param("cnt") int cnt) throws DataAccessException;
	
	@Select("select count(*) from post where board_no=#{board_no}")
	public int selectPostCntByBoardNo(@Param("board_no") int board_no) throws DataAccessException;
	
	
	@Select("select * from post where board_no=#{board_no} and "
			+ "(title like concat('%' ,#{keyword}, '%') or content like concat('%' ,#{keyword}, '%')) "
			+ "order by post_no desc limit #{offset}, #{cnt}")
	public List<PostDto> selectPostListByKeyword(@Param("board_no") int board_no,
			@Param("offset") int offset, @Param("cnt") int cnt, @Param("keyword") String keyword) throws DataAccessException;
	
	@Select("select count(*) from post where board_no=#{board_no} and "
			+ "(title like concat('%',#{keyword},'%') or content like concat('%',#{keyword},'%'))")
	public int selectPostCntByKeyword(@Param("board_no") int board_no, @Param("keyword") String keyword) throws DataAccessException;

	
	@Insert("insert into post(board_no,title,content,create_date,update_date,userid,hit_cnt) "
			+ "values(#{board_no}, #{title}, #{content}, now(), now(), #{userid}, 0)")
	@Options(useGeneratedKeys=true, keyProperty="post_no")
	public void insertPost(PostDto dto) throws DataAccessException;
	
	@Select("select * from post where post_no=#{post_no}")
	public PostDto selectPostByPostNo(@Param("post_no") int post_no) throws DataAccessException;
	
	@Update("update post set hit_cnt = hit_cnt + 1 where post_no=#{post_no}")
	public void updateHitCnt(@Param("post_no") int post_no) throws DataAccessException;
	
	@Delete("delete from post where post_no=#{post_no}")
	public void deletePost(@Param("post_no") int post_no) throws DataAccessException;
	
	@Update("update post set title=#{title}, content=#{content}, update_date=now() where post_no=#{post_no}")
	public void updatePost(PostDto dto) throws DataAccessException;
	
	@Insert("insert into reply values(null, #{post_no}, #{userid}, #{comment}, now(), now())")
	public void insertReply(ReplyDto dto) throws DataAccessException;
	
	@Select("select * from reply where post_no=#{post_no} order by reply_no desc")
	public List<ReplyDto> selectReply(@Param("post_no") int post_no) throws DataAccessException;
	
	@Select("select * from board")
	public List<BoardDto> selectBoardList() throws DataAccessException;
	
	@Select("select * from board where board_no=#{board_no}")
	public BoardDto selectBoard(@Param("board_no") int board_no) throws DataAccessException;
	
	@Select("select board_no from post where post_no=#{post_no}")
	public int selectBoardNoByPostNo(@Param("post_no") int post_no) throws DataAccessException;
	
}
