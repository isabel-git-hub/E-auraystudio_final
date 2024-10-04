package com.example.AurayStudio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AurayStudio.dao.ItemDao;
import com.example.AurayStudio.dto.ItemDto;
import com.example.AurayStudio.dto.ItemimgDto;

@Service
public class ItemServiceImpl implements ItemService {
	private final ItemDao itemdao;

	public ItemServiceImpl (ItemDao itemdao) {
		this.itemdao = itemdao ;
	}
	
	@Override
	public List<ItemDto> getAllItems() {
		return itemdao.findAll();
	}

	@Override
	public void addItem(ItemDto itemdto) {
		itemdao.insertItem(itemdto);
	}

	@Override
	public void uploadImg(ItemimgDto imgdto) {
		itemdao.insertImg(imgdto);
	}

	@Override
	public List<ItemDto> getItemList() {
		return itemdao.selectItemList();
	}

	@Override
	public ItemimgDto downloadImg(String y_no) {
		return itemdao.selectImgByY_no(y_no);
	}

	@Override
	public void deleteItem(String y_no) {
		itemdao.deleteItem(y_no);
	}

	@Override
	public List<ItemDto> getItemByKKind(String kKind) {
		return itemdao.getItemsByKKind(kKind);
	}

	@Override
	public void deleteImg(String y_no) {
		itemdao.deleteImg(y_no);
	}

	@Override
	public ItemDto findByYNo(String y_no) {
		return itemdao.findByYNo(y_no) ;
	}
	
	@Override
	public List<ItemDto> getItemsWithPaging(int page, int size) {
		int offset = (page - 1) * size;  // 0부터 시작하는 오프셋 계산
        return itemdao.selectPagedItems(size, offset);
	}
	
	@Override
	public int getTotalItemCount() {
		return itemdao.getTotalItemCount();
	}
	
//	@Override
//	public List<ItemDto> getItemsByCategoryWithPaging(String category, int page, int size) {
//		if (page < 1) page = 1;
//		int offset = (page - 1) * size;
//		 System.out.println("Category: " + category + ", Page: " + page + ", Size: " + size + ", Offset: " + offset);
//		return itemdao.findItemsByCategory(category, offset, size);
//	}
	@Override
	public List<ItemDto> getItemsByCategoryWithPaging(String category, int page, int size) {
	    int offset = (page - 1) * size;  // offset 계산
	    List<ItemDto> items = itemdao.findItemsByCategory(category, size, offset);
	    System.out.println("Category: " + category + ", Page: " + page + ", Size: " + size + ", Offset: " + offset);
	    System.out.println("Items Size: " + items.size()); // 아이템 크기 확인
	    return items;
	}


	@Override
	public int getTotalItemCountByCategory(String category) {
		return itemdao.countItemsByCategory(category);
	}
	
//	@Override
//	public List<ItemDto> getAllKitchens() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<ItemDto> getKitchensWithPaging(Integer page, Integer size) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public int getTotalKitchenCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
