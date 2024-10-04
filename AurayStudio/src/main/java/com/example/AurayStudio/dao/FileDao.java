package com.example.AurayStudio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.example.AurayStudio.dto.FileDto;


@Mapper
public interface FileDao {
	@Insert("insert into filemng values(null, #{file_name}, #{file_path}, #{org_file_name}, #{userid}, #{post_no})")
	public void insertFile(FileDto file) throws DataAccessException;
	
	@Select("select * from filemng where post_no=#{post_no}")
	public List<FileDto> selectFileByPostNo(@Param("post_no")int post_no) throws DataAccessException;
	
	@Select("select * from filemng where id=#{id}")
	public FileDto selectFileById(@Param("id") int id) throws DataAccessException;
	
	@Delete("delete from filemng where id=#{id}")
	public void deleteFileById(@Param("id") int id) throws DataAccessException;
	
}
