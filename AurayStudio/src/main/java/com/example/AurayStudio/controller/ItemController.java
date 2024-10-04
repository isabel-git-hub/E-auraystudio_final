package com.example.AurayStudio.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AurayStudio.dto.ItemDto;
import com.example.AurayStudio.dto.ItemimgDto;
import com.example.AurayStudio.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ItemController {
	private final ItemService itemservice;
	
	// 제품보기
	@GetMapping("")
	public String product (Model model) {
		model.addAttribute("product", "제품보기");
		return "product";
	}
	// 단열재
	@GetMapping("insulation")
	public String Insulation() {
		return "product/insulation";
	}
	
	// wall 이미지 불러오는 코드
	@GetMapping("window/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgWindow(@PathVariable("y_no")String y_no) { 
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
	
	// 창호 - 일반창
	@GetMapping("/window/normal")
	public String Normal(Model model) {
		List<ItemDto> windows = itemservice.getItemByKKind("일반창") ;
        model.addAttribute("windows", windows);
        return "product/window/normal";
	}
	
	@GetMapping("/window/normal/{y_no}")
    public String NormalPage(@PathVariable("y_no")String y_no, Model model) {
        model.addAttribute("windowA", itemservice.findByYNo(y_no));
        return "product/window/normal/windowAproduct";
    }
	
	// 창호 - 시스템
	@GetMapping("/window/system")
	public String System(Model model) {
		List<ItemDto> windows = itemservice.getItemByKKind("시스템창") ;
		model.addAttribute("windows", windows);
		return "product/window/system";
	}
	
	@GetMapping("/window/system/{y_no}")
	public String SystemPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("windowB", itemservice.findByYNo(y_no));
		return "product/window/system/windowBproduct";
	}
	
	// wall 이미지 불러오는 코드
	@GetMapping("wall/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgWall(@PathVariable("y_no")String y_no) { 
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
	
	// wall
	@GetMapping("/wall")
	public String Wall() {
		return "product/wall";
	}

	// wall - 스타일월
	@GetMapping("/wall/style")
	public String StyleWall(Model model) {
		List<ItemDto> walls = itemservice.getItemByKKind("스타일월") ;
        model.addAttribute("walls", walls);
        return "product/wall/style";
	}
	
	@GetMapping("/wall/style/{y_no}")
    public String StyleWallPage(@PathVariable("y_no")String y_no, Model model) {
        model.addAttribute("wallA", itemservice.findByYNo(y_no));
        return "product/wall/style/wallAproduct";
    }
	
	// wall - 월판넬
	@GetMapping("/wall/panel")
	public String WallPanel(Model model) {
		List<ItemDto> walls = itemservice.getItemByKKind("벽장식(월판넬)") ;
		model.addAttribute("walls", walls);
		return "product/wall/panel";
	}
	
	@GetMapping("/wall/panel/{y_no}")
	public String WallPanelPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("wallB", itemservice.findByYNo(y_no));
		return "product/wall/panel/wallBproduct";
	}
	
	// 타일 이미지 불러오는 코드
	@GetMapping("tile/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgTile(@PathVariable("y_no")String y_no) { 
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
	
	@GetMapping("/tile/tile")
	public String Tile(Model model) {
		List<ItemDto> tiles = itemservice.getItemByKKind("타일") ;
        model.addAttribute("tiles", tiles);
        return "product/tile/tile";
	}
	
	@GetMapping("/tile/tile/{y_no}")
    public String TilePage(@PathVariable("y_no")String y_no, Model model ) {
        model.addAttribute("tile", itemservice.findByYNo(y_no));
        return "product/tile/tile/tileproduct";
    }
	
	// 몰딩 이미지 불러오는 코드
	@GetMapping("moulding/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgMoulding(@PathVariable("y_no")String y_no) { 
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
	
	// 몰딩
	@GetMapping("/moulding/moulding")
	public String Moulding(Model model) {
		List<ItemDto> mouldings = itemservice.getItemByKKind("몰딩") ;
        model.addAttribute("mouldings", mouldings);
        return "product/moulding/moulding";
	}
	
	@GetMapping("/moulding/moulding/{y_no}")
    public String MouldingPage(@PathVariable("y_no")String y_no, Model model ) {
        model.addAttribute("moulding", itemservice.findByYNo(y_no));
        return "product/moulding/moulding/mouldingproduct";
    }
	
	// 필름 이미지 불러오는 코드
	@GetMapping("film/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgFilm(@PathVariable("y_no")String y_no) { 
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
	
	// 필름
	@GetMapping("/film")
	public String Film() {
		return "product/film";
	}
	
	// 필름 - 솔리드
    @GetMapping("/film/solid")
    public String filmSolid(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("솔리드") ;
        model.addAttribute("films", films);
        return "product/film/solid";
    }
    
    @GetMapping("/film/solid/{y_no}")
    public String filmSolidPage(@PathVariable("y_no")String y_no, Model model ) {
        model.addAttribute("filmA", itemservice.findByYNo(y_no));
        return "product/film/solid/filmAproduct";
    }
    
    // 필름 - 메탈
    @GetMapping("/film/metal")
    public String filmMetal(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("메탈") ;
    	model.addAttribute("films", films);
    	return "product/film/metal";
    }
    
    @GetMapping("/film/metal/{y_no}")
    public String filmMetalPage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmB", itemservice.findByYNo(y_no));
    	return "product/film/metal/filmBproduct";
    }
    
    // 필름 - 우드
    @GetMapping("/film/wood")
    public String filmWood(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("우드") ;
    	model.addAttribute("films", films);
    	return "product/film/wood";
    }
    
    @GetMapping("/film/wood/{y_no}")
    public String filmWoodPage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmC", itemservice.findByYNo(y_no));
    	return "product/film/wood/filmCproduct";
    }
    
    // 필름 - 스톤
    @GetMapping("/film/stone")
    public String filmStone(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("스톤") ;
    	model.addAttribute("films", films);
    	return "product/film/stone";
    }
    
    @GetMapping("/film/stone/{y_no}")
    public String filmStonePage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmD", itemservice.findByYNo(y_no));
    	return "product/film/stone/filmDproduct";
    }
    
    // 필름 - 텍스타일
    @GetMapping("/film/textile")
    public String filmTextile(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("텍스타일") ;
    	model.addAttribute("films", films);
    	return "product/film/textile";
    }
    
    @GetMapping("/film/textile/{y_no}")
    public String filmTextilePage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmE", itemservice.findByYNo(y_no));
    	return "product/film/textile/filmEproduct";
    }
    
    // 필름 - 레더
    @GetMapping("/film/leather")
    public String filmLeather(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("레더") ;
    	model.addAttribute("films", films);
    	return "product/film/leather";
    }
    
    @GetMapping("/film/leather/{y_no}")
    public String filmLeatherPage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmF", itemservice.findByYNo(y_no));
    	return "product/film/leather/filmFproduct";
    }
    
    // 필름 - 인테리어 보드
    @GetMapping("/film/interiorboard")
    public String filmInteriorBoard(Model model) {
    	List<ItemDto> films = itemservice.getItemByKKind("인테리어 보드") ;
    	model.addAttribute("films", films);
    	return "product/film/interiorboard";
    }
    
    @GetMapping("/film/interiorboard/{y_no}")
    public String filmInteriorBoardPage(@PathVariable("y_no")String y_no, Model model ) {
    	model.addAttribute("filmG", itemservice.findByYNo(y_no));
    	return "product/film/interiorboard/filmGproduct";
    }
	
	// 바스 이미지 불러오는 코드
	@GetMapping("bath/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgBath(@PathVariable("y_no")String y_no) { 
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
	
	// 바스
	@GetMapping("/bath")
	public String Bath() {
		return "product/bath";
	}
	
	// 바스 - 에센셜
	@GetMapping("/bath/essential")
	public String Essential(Model model) {
		List<ItemDto>baths = itemservice.getItemByKKind("에센셜");
	    model.addAttribute("baths", baths);
	    return "product/bath/essential";
	}
	
	@GetMapping("/bath/essential/{y_no}")
	public String EssentialPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("bathA", itemservice.findByYNo(y_no));
		return "product/bath/essential/bath1product";
	}
	
	// 바스 - 타임리스
	@GetMapping("/bath/timeless")
	public String Timeless(Model model) {
		List<ItemDto>baths = itemservice.getItemByKKind("타임리스");
		model.addAttribute("baths", baths);
		return "product/bath/timeless";
	}
	
	@GetMapping("/bath/timeless/{y_no}")
	public String TimelessPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("bathB", itemservice.findByYNo(y_no));
		return "product/bath/timeless/bath2product";
	}
	
	// 바스 - 구성기기
	@GetMapping("/bath/fixtures")
	public String Fixtures(Model model) {
		List<ItemDto>baths = itemservice.getItemByKKind("바스 구성기기");
		model.addAttribute("baths", baths);
		return "product/bath/fixtures";
	}
	
	@GetMapping("/bath/fixtures/{y_no}")
	public String FixturesPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("bathC", itemservice.findByYNo(y_no));
		return "product/bath/fixtures/bath3product";
	}
	
	// 벽지 이미지 불러오는 코드
	@GetMapping("wallpaper/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgWallpaper(@PathVariable("y_no")String y_no) { 
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
	
	// 실크
	@GetMapping("/wallpaper/silk")
	public String Silk(Model model) {
		List<ItemDto>wallpapers = itemservice.getItemByKKind("실크");
		model.addAttribute("wallpapers", wallpapers);
		return "product/wallpaper/silk";
	}
	
	@GetMapping("/wallpaper/silk/{y_no}")
	public String SilkPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("wallpaperA", itemservice.findByYNo(y_no));
		return "product/wallpaper/silk/WA_product";
	}
	
	// 합지
	@GetMapping("/wallpaper/synthetic")
	public String Synthetic(Model model) {
		List<ItemDto>wallpapers = itemservice.getItemByKKind("합지");
		model.addAttribute("wallpapers", wallpapers);
		return "product/wallpaper/synthetic";
	}
	
	@GetMapping("/wallpaper/synthetic/{y_no}")
	public String SyntheticPage(@PathVariable("y_no")String y_no, Model model) {
		model.addAttribute("wallpaperB", itemservice.findByYNo(y_no));
		return "product/wallpaper/synthetic/WB_product";
	}
	
	// 페인트 이미지 불러오는 코드
	@GetMapping("paint/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgPaint(@PathVariable("y_no")String y_no) { 
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
	
	// 페인트
	@GetMapping("/paint/paint")
	public String Paint (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("페인트") ;
		model.addAttribute("paints", paints);
		return "product/paint/paint";
	}
	
	@GetMapping ("/paint/paint/{y_no}")
	public String PaintA (@PathVariable("y_no") String y_no , Model model) {
		System.out.println(y_no);
		model.addAttribute("paintA", itemservice.findByYNo(y_no)) ;
		return "product/paint/paint/PA_product" ;
	}
	
	// 방수 페인트
	@GetMapping("/paint/waterproof")
	public String PaintWaterproof (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("방수페인트") ;
		model.addAttribute("paints", paints) ;
		return "product/paint/waterproof" ;
	}
	
	@GetMapping ("/paint/waterproof/{y_no}")
	public String PaintWaterproofA (@PathVariable("y_no") String y_no , Model model) {
		model.addAttribute("waterproofA", itemservice.findByYNo(y_no)) ;
		return "product/paint/waterproof/PB_product" ;
	}
	
	// 프라이머
	@GetMapping("/paint/primer")
	public String PaintPrimer (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("프라이머") ;
		model.addAttribute("paints", paints) ;
		return "product/paint/primer" ;
	}
	
	@GetMapping ("/paint/primer/{y_no}")
	public String PaintPrimerA (@PathVariable("y_no") String y_no , Model model) {
		model.addAttribute("primerA", itemservice.findByYNo(y_no)) ;
		return "product/paint/primer/PC_product" ;
	}
	
	// 에폭시
	@GetMapping("/paint/epoxy")
	public String PaintEpoxy (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("에폭시") ;
		model.addAttribute("paints", paints) ;
		return "product/paint/epoxy" ;
	}
	
	@GetMapping ("/paint/epoxy/{y_no}")
	public String PaintEpoxyA (@PathVariable("y_no") String y_no , Model model) {
		model.addAttribute("epoxyA", itemservice.findByYNo(y_no)) ;
		return "product/paint/epoxy/PD_product" ;
	}
	
	// 신나
	@GetMapping("/paint/thinner")
	public String PaintThinner (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("신나") ;
		model.addAttribute("paints", paints) ;
		return "product/paint/thinner" ;
	}
	
	@GetMapping ("/paint/thinner/{y_no}")
	public String PaintThinnerA (@PathVariable("y_no") String y_no , Model model) {
		model.addAttribute("thinnerA", itemservice.findByYNo(y_no)) ;
		return "product/paint/thinner/PE_product" ;
	}
	
	// 탄성코트
	@GetMapping("/paint/elastic")
	public String PaintElastic (Model model) {
		List<ItemDto> paints = itemservice.getItemByKKind("탄성코트") ;
		model.addAttribute("paints", paints) ;
		return "product/paint/elastic" ;
	}
	
	@GetMapping ("/paint/elastic/{y_no}")
	public String PaintElasticA (@PathVariable("y_no") String y_no , Model model) {
		model.addAttribute("elasticA", itemservice.findByYNo(y_no)) ;
		return "product/paint/elastic/PF_product" ;
	}
	
	// 주방 이미지 불러오는 코드
	@GetMapping("kitchen/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgKitchen(@PathVariable("y_no")String y_no) { 
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
	
	// 키친 (인조대리석)
	@GetMapping("/kitchen/echo")
    public String kitchenEcho(Model model) {
        List<ItemDto> kitchens = itemservice.getItemByKKind("인조대리석");
        model.addAttribute("kitchens", kitchens);
        return "product/kitchen/echo";
    }
	
	@GetMapping("/kitchen/echo/{y_no}")
	public String echo(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("echo", itemservice.findByYNo(y_no));
	    return "product/kitchen/echo/KA_product"; // 이 경로가 실제 템플릿 위치와 일치하는지 확인하세요
	}

    // 키친 (이스톤)
    @GetMapping("/kitchen/vista")
    public String kitchenVista(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("이스톤");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/vista";
    }
    
	@GetMapping("/kitchen/vista/{y_no}")
	public String vista(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("vista", itemservice.findByYNo(y_no));
	    return "product/kitchen/vista/KB_product";
	}
    
    // 키친 (포세린)
    @GetMapping("/kitchen/signature")
    public String kitchenSignature(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("포세린");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/signature";
    }
    
	@GetMapping("/kitchen/signature/{y_no}")
	public String signature(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("signature", itemservice.findByYNo(y_no));
	    return "product/kitchen/signature/KC_product";
	}
	
    // 수전
    @GetMapping("/kitchen/faucet")
    public String kitchenFaucet(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("수전");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/faucet";
    }
    
	@GetMapping("/kitchen/faucet/{y_no}")
	public String faucet(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("faucet", itemservice.findByYNo(y_no));
	    return "product/kitchen/faucet/KD_product";
	}
	
    // 싱크볼
    @GetMapping("/kitchen/sink")
    public String kitchenSink(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("싱크볼");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/sink";
    }
    
	@GetMapping("/kitchen/sink/{y_no}")
	public String sink(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("sink", itemservice.findByYNo(y_no));
	    return "product/kitchen/sink/KE_product";
	}
	
    // 팬트리장, 인출망장
    @GetMapping("/kitchen/pantry")
    public String kitchenPantry(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("팬트리장,인출망장");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/pantry";
    }
    
	@GetMapping("/kitchen/pantry/{y_no}")
	public String pantry(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("pantry", itemservice.findByYNo(y_no));
	    return "product/kitchen/pantry/KF_product";
	}
	
    // 후드
    @GetMapping("/kitchen/hood")
    public String kitchenHood(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("후드");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/hood";
    }
    
	@GetMapping("/kitchen/hood/{y_no}")
	public String hood(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("hood", itemservice.findByYNo(y_no));
	    return "product/kitchen/hood/KG_product";
	}
	
    // 기타
    @GetMapping("/kitchen/etc")
    public String kitchenEtc(Model model) {
    	List<ItemDto> kitchens = itemservice.getItemByKKind("기타");
    	model.addAttribute("kitchens", kitchens);
        return "product/kitchen/etc";
    }
    
	@GetMapping("/kitchen/etc/{y_no}")
	public String etc(@PathVariable("y_no") String y_no, Model model) {
	    model.addAttribute("etc", itemservice.findByYNo(y_no));
	    return "product/kitchen/etc/KH_product";
	}
	
	// 중문 이미지 불러오는 코드
	@GetMapping("innergate/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgInnergate(@PathVariable("y_no")String y_no) { 
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

	// 3연동중문
	@GetMapping("/innergate/jungmun3")
	  public String Inner3jungmun(Model model) {
	  	List<ItemDto> innergates = itemservice.getItemByKKind("3연동 중문");
	  	model.addAttribute("innergates", innergates);
	      return "product/innergate/jungmun3";
	  }
	
	@GetMapping("/innergate/jungmun3/{y_no}")
	public String Inner3jungmunA(@PathVariable("y_no") String y_no, Model model) {
	  model.addAttribute("innergateA", itemservice.findByYNo(y_no)) ;
	  return "product/innergate/jungmun3/IA_product";
	}
	
	// 양개중문
	@GetMapping("/innergate/double_sided")
	  public String InnerDouble_sided(Model model) {
	  	List<ItemDto> innergates = itemservice.getItemByKKind("양개중문");
	  	model.addAttribute("innergates", innergates);
	      return "product/innergate/double_sided";
	  }
	
	@GetMapping("/innergate/double_sided/{y_no}")
	  public String InnerDouble_sidedB(@PathVariable("y_no") String y_no, Model model) {
	      model.addAttribute("innergateB", itemservice.findByYNo(y_no)) ;
	      return "product/innergate/double_sided/IB_product";
	  }
	
	// 슬림 알루미늄 중문
	@GetMapping("/innergate/aluminum")
	public String InnerAaluminum(Model model) {
		List<ItemDto> innergates = itemservice.getItemByKKind("슬림 알루미늄 중문");
		model.addAttribute("innergates", innergates);
	  return "product/innergate/aluminum";
	}
	
	@GetMapping("/innergate/aluminum/{y_no}")
	public String InnerAaluminumC(@PathVariable("y_no") String y_no, Model model) {
	  model.addAttribute("innergateC", itemservice.findByYNo(y_no)) ;
	  return "product/innergate/aluminum/IC_product";
	}
	
	// 폴딩도어
	@GetMapping("/innergate/folding")
	public String InnerCabinet(Model model) {
		List<ItemDto> innergates = itemservice.getItemByKKind("폴딩도어");
		model.addAttribute("innergates", innergates);
	  return "product/innergate/folding";
	}
	
	@GetMapping("/innergate/folding/{y_no}")
	public String InnerCabinetD(@PathVariable("y_no") String y_no, Model model) {
	  model.addAttribute("innergateD", itemservice.findByYNo(y_no)) ;
	  return "product/innergate/folding/ID_product";
	}
	
	// 파티션
	@GetMapping("/innergate/partition")
	public String InnerSliding(Model model) {
		List<ItemDto> innergates = itemservice.getItemByKKind("파티션");
		model.addAttribute("innergates", innergates);
	  return "product/innergate/partition";
	}
	
	@GetMapping("/innergate/partition/{y_no}")
	public String InnerSlidingE(@PathVariable("y_no") String y_no, Model model) {
	  model.addAttribute("innergateE", itemservice.findByYNo(y_no)) ;
	  return "product/innergate/partition/IE_product";
	}
	
	// 도어 이미지 불러오는 코드
	@GetMapping("door/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgDoor(@PathVariable("y_no")String y_no) { 
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

	// 도어 (ABS 도어)
	@GetMapping("/door/doorabs")
	  public String DoorABS(Model model) {
	  	List<ItemDto> doors = itemservice.getItemByKKind("ABS 도어");
	  	model.addAttribute("doors", doors);
	      return "product/door/doorabs";
	  } 
	
		 @GetMapping("/door/absdoor/{y_no}")
		 public String DoorABSA(@PathVariable("y_no") String y_no ,  Model model) {
			 model.addAttribute("doorA", itemservice.findByYNo(y_no)) ;
			 return "product/door/absdoor/DA_product" ;
		 }
	
	// 도어 (기능성 도어)
	@GetMapping("/door/craftinesse")
	  public String DoorCraftinesse(Model model) {
	  	List<ItemDto> doors = itemservice.getItemByKKind("기능성 도어");
	  	model.addAttribute("doors", doors);
	      return "product/door/craftinesse";
	  }
	
	@GetMapping("/door/craftinesse/{y_no}")
	public String DoorCraftinesseA(@PathVariable("y_no") String y_no ,  Model model) {
		 model.addAttribute("doorB", itemservice.findByYNo(y_no)) ;
		 return "product/door/craftinesse/DB_product" ;
	}
	
	// 도어 (도어 하드웨어)
	@GetMapping("/door/hardware")
	public String DoorHardware(Model model) {
		List<ItemDto> doors = itemservice.getItemByKKind("도어 하드웨어");
		model.addAttribute("doors", doors);
	  return "product/door/hardware";
	}
	
	@GetMapping("/door/hardware/{y_no}")
	public String DoorHardwareA(@PathVariable("y_no") String y_no ,  Model model) {
		 model.addAttribute("doorC", itemservice.findByYNo(y_no)) ;
		 return "product/door/hardware/DC_product" ;
	}
	// 도어 (도어 시스템)
	@GetMapping("/door/system")
	public String DoorSystem(Model model) {
		List<ItemDto> doors = itemservice.getItemByKKind("도어 시스템");
		model.addAttribute("doors", doors);
	  return "product/door/system";
	}
	
	@GetMapping("/door/system/{y_no}")
	public String DoorSystemA(@PathVariable("y_no") String y_no ,  Model model) {
		 model.addAttribute("doorD", itemservice.findByYNo(y_no)) ;
		 return "product/door/system/DD_product" ;
	}
	
	// 붙박이장 이미지 불러오는 코드
	@GetMapping("built_in/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgBuilt_in(@PathVariable("y_no")String y_no) { 
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


	// 여닫이
	@GetMapping("/built_in/casement")
	public String BuiltCasement(Model model) {
		List<ItemDto> built_ins = itemservice.getItemByKKind("여닫이");
		model.addAttribute("built_ins", built_ins);
		return "product/built_in/casement";
	}
	
	@GetMapping("/built_in/casement/{y_no}")
	public String BuiltCasement(@PathVariable("y_no") String y_no, Model model) {
	  model.addAttribute("builtInC", itemservice.findByYNo(y_no));
	  return "product/built_in/casement/BC_product";
	}
	
	
	// 드레스룸
	@GetMapping("/built_in/dressing")
	public String BuiltCraftinesse(Model model) {
		List<ItemDto> built_ins = itemservice.getItemByKKind("드레스룸");
		model.addAttribute("built_ins", built_ins);
		return "product/built_in/dressing";
	}
	
	@GetMapping("/built_in/dressing/{y_no}")
	public String BuiltCasementD(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("builtInD", itemservice.findByYNo(y_no));
		return "product/built_in/dressing/BD_product";
	}
	
	// 현관장
	@GetMapping("/built_in/entrancehall")
	public String BuiltEntrancehall(Model model) {
		List<ItemDto> built_ins = itemservice.getItemByKKind("현관장");
		model.addAttribute("built_ins", built_ins);
		return "product/built_in/entrancehall";
	}
	
	@GetMapping("/built_in/entrancehall/{y_no}")
	public String BuiltEntrancehall(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("builtInE", itemservice.findByYNo(y_no));
		return "product/built_in/entrancehall/BE_product";
	}
	
	// 수납장
	@GetMapping("/built_in/cabinet")
	public String BuiltCabinet(Model model) {
		List<ItemDto> built_ins = itemservice.getItemByKKind("수납장");
		model.addAttribute("built_ins", built_ins);
		return "product/built_in/cabinet";
	}
	
	@GetMapping("/built_in/cabinet/{y_no}")
	public String BuiltCabinet(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("builtInM", itemservice.findByYNo(y_no));
		return "product/built_in/cabinet/BM_product";
	}
	
	// 슬라이딩
	@GetMapping("/built_in/sliding")
	public String BuiltSliding(Model model) {
		List<ItemDto> built_ins = itemservice.getItemByKKind("슬라이딩");
		model.addAttribute("built_ins", built_ins);
		return "product/built_in/sliding";
	}
	
	@GetMapping("/built_in/sliding/{y_no}")
	public String BuiltSliding(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("builtInS", itemservice.findByYNo(y_no));
		return "product/built_in/sliding/BS_product";
	}
	
	// 바닥재 이미지 불러오는 코드
	@GetMapping("flooring/downloadImg/{y_no}") public ResponseEntity<Resource>
	downloadImgFlooring(@PathVariable("y_no")String y_no) { 
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

	 // 원목마루
	@GetMapping("/flooring/wood")
	public String Wood(Model model) {
		List<ItemDto> floorings = itemservice.getItemByKKind("원목마루");
		model.addAttribute("floorings", floorings);
		return "product/flooring/wood";
	}

	@GetMapping("/flooring/wood/{y_no}")
	public String Woodpage(@PathVariable("y_no") String y_no, Model model) {
		System.out.println(y_no);
	    model.addAttribute("flooringA", itemservice.findByYNo(y_no));
	    return "product/flooring/wood/FA_product";
	}


	// 강마루
	@GetMapping("/flooring/gang")
	public String Gang(Model model) {
		List<ItemDto> floorings = itemservice.getItemByKKind("강마루");
		model.addAttribute("floorings", floorings);
		return "product/flooring/gang";
	}
	
	@GetMapping("/flooring/gang/{y_no}")
	public String GangPage(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("flooringB", itemservice.findByYNo(y_no));
		return "product/flooring/gang/FB_product";
	}

	// 시트
	@GetMapping("/flooring/sheet")
	public String Sheet(Model model) {
		List<ItemDto> floorings = itemservice.getItemByKKind("시트");
		model.addAttribute("floorings", floorings);
		return "product/flooring/sheet";
	}
	
	@GetMapping("/flooring/sheet/{y_no}")
	public String SheetPage(@PathVariable("y_no") String y_no, Model model) {
		model.addAttribute("flooringC", itemservice.findByYNo(y_no));
		return "product/flooring/sheet/FC_product";
	}
}
