package com.example.AurayStudio.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.AurayStudio.dto.ItemDto;
import com.example.AurayStudio.dto.ItemimgDto;
import com.example.AurayStudio.service.ItemService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	private final ItemService itemservice;
	
	@GetMapping({"/", "/index"})
	public String indexPage(HttpSession session, Model model) {
		// 세션에서 사용자 정보 가져오기
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("greeting", "안녕하세요, " + loggedInUser + "님");
        }
		return "index";
	}
	
	@GetMapping("/company")
	public String companyForm() {
		return "company";
	}
	
	@GetMapping("/shop")
	public String shopPage (Model model) {
		return "/shop" ;
	}
	
	@GetMapping("/mypage/registration")
	public String registration(Model model, 
	                           @RequestParam(value = "category", required = false) String category,
	                           @RequestParam(value = "page", defaultValue = "1") Integer page, 
	                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
	    System.out.println("Category: " + category);
	    System.out.println("Page: " + page);
	    System.out.println("Size: " + size);

	    // 페이지 번호가 1 미만인 경우 1로 설정
	    if (page < 1) {
	        page = 1;
	    }

	    List<ItemDto> items; 
	    int totalItems;

	    if (category == null || category.isEmpty()) {
	        items = itemservice.getItemsWithPaging(page, size);
	        totalItems = itemservice.getTotalItemCount();
	    } else {
	        items = itemservice.getItemsByCategoryWithPaging(category, page, size);
	        totalItems = itemservice.getTotalItemCountByCategory(category);
	    }

	    System.out.println("Items Size: " + items.size());
	    System.out.println("Total Items: " + totalItems);
	    
	    int totalPages = (int) Math.ceil((double) totalItems / size);

	    // 페이지 그룹 계산
	    int pageGroupSize = 10;  // 한 페이지 그룹에 몇 개의 페이지를 보여줄지 설정
	    int currentGroup = (page - 1) / pageGroupSize;  // 현재 페이지 그룹
	    int startPage = currentGroup * pageGroupSize + 1;  // 시작 페이지 번호
	    int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);  // 마지막 페이지 번호

	    model.addAttribute("items", items);
	    model.addAttribute("category", category);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("hasPrevPage", currentGroup > 0);
	    model.addAttribute("hasNextPage", endPage < totalPages);
	    model.addAttribute("size", size);

	    return "registration";
	}



		
	// 제품 목록 페이지
//		@GetMapping("/mypage/registration")
//		public String registration(Model model, 
//		                           @RequestParam(value = "page", defaultValue = "1") Integer page, 
//		                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
//		    // 기본값을 명시적으로 확인 (null인 경우 대비)
//		    if (size == null) size = 10;
//
//		    List<ItemDto> items = itemservice.getItemsWithPaging(page, size);
//		    int totalItems = itemservice.getTotalItemCount();
//		    int totalPages = (int) Math.ceil((double) totalItems / size);
//
//		    // 페이지 그룹 계산
//		    int pageGroupSize = 10;  // 한 페이지 그룹에 몇 개의 페이지를 보여줄지 설정
//		    int currentGroup = (page - 1) / pageGroupSize;  // 현재 페이지 그룹
//		    int startPage = currentGroup * pageGroupSize + 1;  // 시작 페이지 번호
//		    int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);  // 마지막 페이지 번호
//
//		    // 페이징 정보 추가
//		    model.addAttribute("items", items);
//		    model.addAttribute("currentPage", page);
//		    model.addAttribute("totalPages", totalPages);
//		    model.addAttribute("startPage", startPage);
//		    model.addAttribute("endPage", endPage);
//		    model.addAttribute("hasPrevPage", currentGroup > 0);
//		    model.addAttribute("hasNextPage", endPage < totalPages);
//		    model.addAttribute("size", size);  // size 값을 모델에 추가하여 템플릿으로 전달
//
//		    return "registration";  // registration.html로 이동
//		}
//		
		// 카테고리별 제품 목록 페이지
//		@GetMapping("/mypage/registration/{category}")
//		public String registrationByCategory(Model model, 
//                                                  @PathVariable("category") String category,
//		                                          @RequestParam(value = "page", defaultValue = "1") Integer page, 
//		                                          @RequestParam(value = "size", defaultValue = "10") Integer size) { // PathVariable로 category 받기
//		    // 기본값을 명시적으로 확인 (null인 경우 대비)
//		    if (size == null) size = 10;
//
//		    List<ItemDto> items = itemservice.getItemsByCategoryWithPaging(category, page, size);
//		    int totalItems = itemservice.getTotalItemCountByCategory(category);
//		    int totalPages = (int) Math.ceil((double) totalItems / size);
//
//		    // 페이지 그룹 계산
//		    int pageGroupSize = 10;  // 한 페이지 그룹에 몇 개의 페이지를 보여줄지 설정
//		    int currentGroup = (page - 1) / pageGroupSize;  // 현재 페이지 그룹
//		    int startPage = currentGroup * pageGroupSize + 1;  // 시작 페이지 번호
//		    int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);  // 마지막 페이지 번호
//
//		    // 페이징 정보 추가
//		    model.addAttribute("items", items);
//		    model.addAttribute("category", category);
//		    model.addAttribute("currentPage", page);
//		    model.addAttribute("totalPages", totalPages);
//		    model.addAttribute("startPage", startPage);
//		    model.addAttribute("endPage", endPage);
//		    model.addAttribute("hasPrevPage", currentGroup > 0);
//		    model.addAttribute("hasNextPage", endPage < totalPages);
//		    model.addAttribute("size", size);  // size 값을 모델에 추가하여 템플릿으로 전달
//
//		    return "registration";  // registration.html로 이동
//		}
    
 // 제품 추가
    @PostMapping("mypage/registration/add")
    public String addItem(Model model, ItemDto itemdto, @RequestParam(value = "file", required = false) MultipartFile[] files) {
        // 경로를 외부 설정에서 불러오거나 하드코딩된 경로를 사용
        final String path = "Y:\\auray main\\진\\AURAY code\\AurayStudio\\src\\main\\resources\\static\\img";
        System.out.println("length="+files.length);
        // Paint 정보 추가
        itemservice.addItem(itemdto);

        // 파일 업로드 처리
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                try {
                    ItemimgDto imgdto = new ItemimgDto();
                    String org_file_name = file.getOriginalFilename();
                    String file_name = UUID.randomUUID().toString().substring(0,8) + "_" + org_file_name;

                    // ImgmngDto에 정보 설정
                    imgdto.setFile_path(path);
                    imgdto.setOrg_file_name(org_file_name);
                    imgdto.setFile_name(file_name);
                    imgdto.setY_no(itemdto.getY_no());

                    System.out.println(path + File.separator + file_name);
                    // 파일을 실제 저장 위치에 저장
                    File destinationFile = new File(path + File.separator + file_name);
                    file.transferTo(destinationFile);

                    // 이미지 정보 저장
                    itemservice.uploadImg(imgdto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 제품 목록을 모델에 추가
        List<ItemDto> list = itemservice.getItemList();
        model.addAttribute("list", list);

        return "redirect:/mypage/registration";
    }
    
    
    
	// 이미지 불러오는 코드
	  @GetMapping("/mypage/registration/downloadImg/{y_no}") public ResponseEntity<Resource>
	  downloadImg(@PathVariable("y_no")String y_no) { 
		  try { 
			  ItemimgDto dto = itemservice.downloadImg(y_no); 
			  Path filePath = Paths.get(dto.getFile_path()).resolve(dto.getFile_name()).normalize();
			  Resource resource = new UrlResource(filePath.toUri()); 
			  if(resource.exists()) { 
				  String encodeFileName = URLEncoder.encode(dto.getOrg_file_name(),
						  StandardCharsets.UTF_8.toString()); 
				  String contentDisposition = "attachment; filename=\"" + encodeFileName + "\""; 
				  
				  return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
						  .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream").body(resource); 
				  } 
				  else { 
					  return ResponseEntity.notFound().build(); 
				  } 
			  }
			  catch(IOException e) { 
				  e.printStackTrace(); 
				  return ResponseEntity.notFound().build(); 
		  } 
	  }
	 
    
    // 제품 삭제
    @GetMapping("/mypage/registration/delete/{y_no}")
    public String deleteItem(@PathVariable("y_no") String y_no) {
        itemservice.deleteItem(y_no);  // y_no로 삭제
        itemservice.deleteImg(y_no);
        return "redirect:/mypage/registration";
    }
}
