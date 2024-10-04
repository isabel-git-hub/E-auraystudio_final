package com.example.AurayStudio.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.AurayStudio.com.Search;
import com.example.AurayStudio.dto.BoardDto;
import com.example.AurayStudio.dto.FileDto;
import com.example.AurayStudio.dto.PostDto;
import com.example.AurayStudio.dto.ReplyDto;
import com.example.AurayStudio.service.BoardService;
import com.example.AurayStudio.service.FileService;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private FileService fileService;
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		// 게시판 메뉴 취득
		List<BoardDto> menu = boardService.getBoardMenu();
		model.addAttribute("menu", menu);
		return "mypage";
	}
	
	@GetMapping("/board/{board_no}")
	public String boardPage(Model model, @PathVariable("board_no") int board_no, 
	                        @RequestParam(value = "keyword", defaultValue = "") String keyword) {
	    // 검색 및 페이지 처리
	    Search search = new Search(5, 5);
	    if (keyword != null && !keyword.isEmpty()) {
	        search.setKeyword(keyword);
	    }
	    
	    // 게시글 리스트 취득
	    List<PostDto> list = service.getPostListByBoard(board_no, search);
	    model.addAttribute("list", list);
	    model.addAttribute("page", search);
	    
	    // 게시판 정보 취득
	    BoardDto board = service.getBoard(board_no);
	    model.addAttribute("board", board);

	    return "board";
	}
	
	@GetMapping("/board/{board_no}/{page}")
	public String boardPage(Model model, @PathVariable("board_no") int board_no, @PathVariable("page") int page, 
			@RequestParam(value = "keyword", defaultValue="") String keyword) {
		Search search =new Search(5, 5);
		search.setPage(page);
		search.setKeyword(keyword);
		// 키워드 파라메타가 있으면 키워드 설정
		if(keyword != null) search.setKeyword(keyword);
		// 게시글 리스트 취득
		List<PostDto> list = service.getPostListByBoard(board_no, search);
		model.addAttribute("list", list);
		model.addAttribute("page", search);
		
		// 게시판 정보 취득
		BoardDto board = service.getBoard(board_no);
		model.addAttribute("board", board);
		
		return "board";
	}
	
	@GetMapping("/search/{board_no}")
	public String searchPost(Model model, @PathVariable("board_no") int board_no, @RequestParam("keyword") String keyword) {
		Search page = new Search(5, 5);
		page.setKeyword(keyword);
		// 게시글 리스트 취득
		List<PostDto> list = service.getPostListByKeyword(board_no, page);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		
		// 게시판 정보 취득
		BoardDto board = service.getBoard(board_no);
		model.addAttribute("board", board);
		
		return "borad";
	}
	
	@GetMapping("/write/{board_no}")
	public String writeForm(Model model, @PathVariable("board_no") int board_no) {
		// 게시판 정보 취득
		BoardDto board = service.getBoard(board_no);
		model.addAttribute("board", board);
		
		return "writeForm";
	}
	@PostMapping("/write")
	public String writePost(Model model, PostDto dto, 
			@RequestParam(value="file", required=false) MultipartFile[] files) {
		final String path = "Y:\\auray main\\진\\AURAY code\\AurayStudio\\repository";
		System.out.println(dto.getBoard_no());
		// 게시판 메뉴 취득
		List<BoardDto> menu = service.getBoardMenu();
		model.addAttribute("menu", menu);
		// 게시글 저장
		dto = service.putPost(dto);
		model.addAttribute("post", dto);
		// 게시판 정보 취득
		BoardDto board = service.getBoard(dto.getBoard_no());
		model.addAttribute("board", board);
		
		// 파일 업로드 처리
		if(files != null) {
			for(MultipartFile file : files) {
				try {
					FileDto fileDto = new FileDto();
					String org_file_name = file.getOriginalFilename();
					String file_name = UUID.randomUUID().toString().substring(0,8) + "_" + org_file_name;
					fileDto.setFile_name(file_name);
					fileDto.setFile_path(path);
					fileDto.setOrg_file_name(org_file_name);
					fileDto.setUserid(dto.getUserid());
					fileDto.setPost_no(dto.getPost_no());
					
					// 실 저장위치에 파일 업로드
					file.transferTo(new File(path + file_name));
					fileService.fileUpload(fileDto);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			List<FileDto> file_list = fileService.fileDownloadList(dto.getPost_no());
			model.addAttribute("file_list", file_list);
		}
		return "viewForm";
	}
	@GetMapping("/view/{post_no}")
	public String viewPost(@PathVariable("post_no") int post_no, Model model) {
		// 게시글 취득
		PostDto dto = service.getPost(post_no);
		service.cntHitCnt(post_no);
		model.addAttribute("post", dto);

		// 게시판 정보 취득
		BoardDto board = service.getBoard(dto.getBoard_no());
		model.addAttribute("board", board);

		// 게시판 메뉴 취득
		List<BoardDto> menu = service.getBoardMenu();
		model.addAttribute("menu", menu);

		// 답급 취득
		List<ReplyDto> reply_list = service.getReply(dto.getPost_no());
		model.addAttribute("reply_list",reply_list);

		// 파일 취득
		List<FileDto> file_list = fileService.fileDownloadList(post_no);
		model.addAttribute("file_list", file_list);
		
		return "viewForm";
	}
	@GetMapping("/delete/{post_no}")
	public String delPost(@PathVariable("post_no") int post_no) {
		int board_no = service.getBoardNo(post_no);
		service.delPost(post_no);
		return "redirect:/board/" + board_no;
	}
	
	@GetMapping("/edit/{post_no}")
	public String editForm(@PathVariable("post_no") int post_no, Model model) {
		// 게시글 취득
		PostDto dto = service.getPost(post_no);
		model.addAttribute("post", dto);
		
		// 게시판 정보 취득
		BoardDto board = service.getBoard(dto.getBoard_no());
		model.addAttribute("board", board);

		return "editForm";
	}
	
	@PostMapping("/edit")
	public String editPost(Model model, PostDto dto) {
		// dto가 null인지 확인
	    if (dto == null) {
	        // 오류 처리 또는 예외 발생
	        throw new IllegalArgumentException("게시글 정보가 없습니다.");
	    }
		// 게시글 수정
	    dto = service.editPost(dto);
	    
	    // 수정된 게시글 정보를 다시 가져와 모델에 추가
		model.addAttribute("post", dto);

		// 게시판 정보 취득
		BoardDto board = service.getBoard(dto.getBoard_no());
		model.addAttribute("board", board);

		// 답글 취득
		List<ReplyDto> reply_list = service.getReply(dto.getPost_no());
		model.addAttribute("reply_list",reply_list);
		
		return "viewForm";
	}
	@PostMapping("/reply")
	public String setReply(Model model, ReplyDto reply) {
		// 답글 등록
		service.putReply(reply);
		List<ReplyDto> reply_list = service.getReply(reply.getPost_no());
		model.addAttribute("reply_list",reply_list);
		// 게시글 취득
		PostDto dto = service.getPost(reply.getPost_no());
		model.addAttribute("post",dto);
		// 게시판 정보 취득
		BoardDto board = service.getBoard(dto.getBoard_no());
		model.addAttribute("board", board);
		// 게시판 메뉴 취득
		List<BoardDto> menu = service.getBoardMenu();
		model.addAttribute("menu", menu);
		
		return "viewForm";
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id")int id){
		try {
			FileDto dto = fileService.fileDownload(id);
			Path filePath = Paths.get(dto.getFile_path()).resolve(dto.getFile_name()).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				String encodeFileName = URLEncoder.encode(dto.getOrg_file_name(), StandardCharsets.UTF_8.toString());
				String contentDisposition = "attachment; filename=\"" + encodeFileName + "\"";
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.body(resource);
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
	
}