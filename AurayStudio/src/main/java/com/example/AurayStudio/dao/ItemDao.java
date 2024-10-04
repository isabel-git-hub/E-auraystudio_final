package com.example.AurayStudio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.example.AurayStudio.dto.FileDto;
import com.example.AurayStudio.dto.ItemDto;
import com.example.AurayStudio.dto.ItemimgDto;

@Mapper
public interface ItemDao {
	
	@Select("select * from item_product")
	List<ItemDto> findAll() throws DataAccessException;
	
	@Insert("insert into item_product (category, y_no, y_name, pro_kind, style, content) values (#{category} ,#{y_no}, #{y_name}, #{pro_kind}, #{style}, #{content})")
	void insertItem(ItemDto itemdto) throws DataAccessException;

	@Insert("insert into item_img values(null, #{file_name}, #{file_path}, #{org_file_name}, #{y_no})")
	void insertImg(ItemimgDto imgdto) throws DataAccessException;

	@Select("select i.y_no, p.y_name, p.pro_kind from item_product p "
			+ "join item_img i on p.y_no = i.y_no")
	List<ItemDto> selectItemList() throws DataAccessException;

	@Select("select * from item_img where y_no=#{y_no}")
	ItemimgDto selectImgByY_no(@Param("y_no")String y_no) throws DataAccessException;

	@Delete("delete from item_product where y_no = #{y_no}")
	void deleteItem(@Param("y_no")String y_no) throws DataAccessException;

	@Delete("delete from item_img where y_no = #{y_no}")
	void deleteImg(@Param("y_no")String y_no) throws DataAccessException;

	@Select("select y_no, y_name, pro_kind, style, content from item_product where pro_kind = #{kKind}")
	List<ItemDto> getItemsByKKind(String kKind) throws DataAccessException;

	@Select("select * from item_product where y_no = #{y_no}")
	ItemDto findByYNo(@Param("y_no")String y_no) throws DataAccessException;
	
	@Select("SELECT * FROM item_product LIMIT #{size} OFFSET #{offset}")
	List<ItemDto> selectPagedItems(@Param("size") int size, @Param("offset") int offset) throws DataAccessException;

	@Select("SELECT COUNT(*) FROM item_product")
	int getTotalItemCount() throws DataAccessException;
	
	@Select("SELECT * FROM item_product WHERE category = #{category} LIMIT #{size} OFFSET #{offset}")
	List<ItemDto> findItemsByCategory(@Param("category") String category, 
	                                          @Param("size") int size,
	                                          @Param("offset") int offset)  throws DataAccessException;
	
	@Select("SELECT COUNT(*) FROM item_product WHERE category = #{category}")
	int countItemsByCategory(@Param("category") String category) throws DataAccessException;

}
