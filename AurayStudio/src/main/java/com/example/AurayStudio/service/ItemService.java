package com.example.AurayStudio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AurayStudio.dto.ItemDto;
import com.example.AurayStudio.dto.ItemimgDto;

@Service
public interface ItemService {

	List<ItemDto> getAllItems();

	void addItem(ItemDto itemdto);

	void uploadImg(ItemimgDto imgdto);

	List<ItemDto> getItemList();

	ItemimgDto downloadImg(String y_no);

	void deleteItem(String y_no);

	void deleteImg(String y_no);

	List<ItemDto> getItemByKKind(String string);

	Object findByYNo(String y_no);
	
	List<ItemDto> getItemsWithPaging(int page, int size);

	int getTotalItemCount();

//	List<ItemDto> getAllKitchens();
//
//	List<ItemDto> getKitchensWithPaging(int page, int size);
//
//	int getTotalKitchenCount();
	
	List<ItemDto> getItemsByCategoryWithPaging(String category, int page, int size);
	
	int getTotalItemCountByCategory(String category);

}
