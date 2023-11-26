package com.multi.campus.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.RecipeService;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.RecipefileVO;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	@Autowired
	RecipeService service;
	
	//자료실 목록
	@GetMapping("/list")
	public String dataList(Model model) {
		model.addAttribute("list", service.dataList());
		return "recipe/recipe";
	}
	//자료실 글등록 폼
	@GetMapping("/write")
	public String dataWrite() {
		return "recipe/recipeWrite";
	}
	//자료실 글등록(업로드, DB)
	@PostMapping("/writeOk")
	@Transactional(rollbackFor={RuntimeException.class, SQLException.class})
	public ModelAndView dataWriteOk(RecipeVO vo, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		//1. request : 제목, 글내용, session : 글쓴이
		vo.setUserid((String)session.getAttribute("logId"));
		
		//2. 파일업로드 (rename)
		//업로드할 위치 폴더(절대주소로 구한다.)구하기
		String path = session.getServletContext().getRealPath("/uploadfile");
		System.out.println("path->"+ path);
		
		// HttpServletRequest -> MultipartHttpServletRequest객체를 구한다.
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		
		//mr객체 내에 파일의 수만큼 MultipartFile객체가 있다.
		List<MultipartFile> filesList = mr.getFiles("filename"); //input 태그의 name 속성값
		
		// 업로드 한 파일목록을 보관 DatafileVO -> List
		List<RecipefileVO> uploadFileList = new ArrayList<RecipefileVO>();
		
		if(filesList != null){//업로드 파일이 있을때
			for(int i=0; i<filesList.size(); i++) {// 첨부된 파일 만큼 반복수행
				MultipartFile mf = filesList.get(i);
				
				String orgFilename = mf.getOriginalFilename();//클라이언트가 선택한 원래 파일명을 구한다.
				
				if(orgFilename!=null && !orgFilename.equals("")){ // if2
					//이미 서버에 같은 파일이 있으면 rename을 수행한다.  car.jpg -> car(1).jpg -> car(2).jpg -> car(3).jpg
					
					File f = new File(path, orgFilename);
					
					if(f.exists()) {// 파일이 있으면 true, 파일이 없으면 false if3
						// rename -> 파일명 찾기
						for(int renameNum = 1; ;renameNum++) {
							//확장자와 파일명분리
							int point = orgFilename.indexOf(".");//점위치구하기
							String filenameNoExt = orgFilename.substring(0, point);//파일명(확장자빼고) car
							String ext = orgFilename.substring(point+1);//확장자 gif
							
							//새로운 파일명 만들기
							String newFilename = filenameNoExt+"("+renameNum+")."+ext; // car(1).jpg
							f = new File(path, newFilename);
							if(!f.exists()) {//새로운 파일명이 서버에 있는지 확인 if4
								orgFilename = newFilename; // 새로 만든 파일이 서버에 없으면 반복중단
								break;
							}//if 4
						}// for2
						
					}//if3
					//업로드
					try {
						mf.transferTo(f);//서버에 실제 업로드 되는 시점.
					}catch(Exception e) {}
					
					//업로드 
					RecipefileVO fVO = new RecipefileVO();
					fVO.setFilename(orgFilename);
					uploadFileList.add(fVO);
				}//if2
			}//for1
			
		}//if1
		
		//============== 업로드 끝 =========================================
		try {
			//3. db에 레코드 추가(원글:1, 업로드한 파일명:N)
			//원글 insert추가 -> no를 구해와야 한다.
			int result = service.dataInsert(vo);
			System.out.println(vo.getRecipeno());
			//업로드 파일명
			for(RecipefileVO fVO: uploadFileList) {
				fVO.setRecipeno(vo.getRecipeno());
			}
			
			int fileResult = service.dataFileInsert(uploadFileList);
			
			//4. 추가 성공하면 -> 자료실목록
			mav.setViewName("redirect:list");
			
		}catch(Exception e) {
			e.printStackTrace();
			//5. 실패하면 레코드와, 파일을 삭제하고 글올리기 폼으로 이동
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			//이미 업로드된 파일을 삭제
			for(RecipefileVO fVO : uploadFileList) {
				File f = new File(path, fVO.getFilename());
				f.delete();
			}
			
			//글등록폼으로 이동
			mav.setViewName("recipe/recipeResult");
			
		}
		return mav;
	}
	//글내용보기
	@GetMapping("/view/{recipeno}")    //@PathVariable: url매핑주소에 변수없이 전송되는 데이터를 request하는 어노테이션
	public ModelAndView dataView(@PathVariable("recipeno") int recipeno ) {
		
		//1.조회수
		service.dataHitCount(recipeno);
		//2. 원글선택(no같은 레코드)
		RecipeVO vo = service.dataSelect(recipeno);
		//3. 원글에 첨부된 파일 선택
		List<RecipefileVO> fileList = service.getDataFile(recipeno);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("fileList", fileList);
		mav.setViewName("recipe/recipeView");
		
		return mav;
	}
	//글수정폼
	@GetMapping("/edit/{recipeno}")
	public ModelAndView dataEdit(@PathVariable("recipeno") int recipeno) {
		ModelAndView mav  = new ModelAndView();
		//원글선택
		mav.addObject("vo", service.dataSelect(recipeno));
		
		List<RecipefileVO> fList = service.getDataFile(recipeno);
		//첨부파일 
		mav.addObject("fList", fList);//목록
		mav.addObject("fileCount", fList.size());//첨부파일의 갯수
		//뷰페이지
		mav.setViewName("recipe/recipeEdit");
		return mav;
	}
	//자료실 글수정(DB)
	@PostMapping("/editOk") 
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	                               //글번호, 글제목, 글내용, 삭제파일(List)
	public ModelAndView dataEditOk(RecipeVO vo, HttpServletRequest request) {
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		//1. 삭제한 파일목록 vo.getDelFile();
		
		//2. 이전에 업로드한 첨부파일을 얻어오기
		List<RecipefileVO> dbFileList = service.getDataFile(vo.getRecipeno());
		
		//3. 새로 업로드된 파일처리
		String path = request.getSession().getServletContext().getRealPath("/uploadfile");
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		List<MultipartFile> mrFileList = mr.getFiles("filename");
		
		
		//새로추가한 파일목록
		List<RecipefileVO> newFileList = fileUpload(path, mrFileList);
		
		
		//데이터베이스 업데이트
		ModelAndView mav = new ModelAndView();
		try {
	
			//A. 삭제파일, 새로업로드파일, DB파일정리
			//삭제 파일이 있으면 dbFileList에서 삭제파일VO제거
			if(vo.getDelFile()!=null && vo.getDelFile().size()>0) {
				for(int i=0; i<vo.getDelFile().size(); i++) {
					String del = vo.getDelFile().get(i);
					for(int j=0; j<dbFileList.size(); j++) {
						RecipefileVO dfVO = dbFileList.get(j); 
						if(del.equals(dfVO.getFilename())) {
							dbFileList.remove(j);
							j--;
						}
					}
				}
			}//삭제파일
			
			//새로업로드된 파일을 dbFile로 추가
			if(newFileList.size()>0) {
			
				for(RecipefileVO dfVO:newFileList) {
					dfVO.setRecipeno(vo.getRecipeno());
					dbFileList.add(dfVO);
				}
			}
			
			
	
			//C. 기존첨부파일은 지우고
			int delResult = service.dataDelete(vo.getRecipeno());
			//D. 첨부파일추가
			int insertResult = service.dataFileInsert(dbFileList);
			//B. 원글업데이트
			int result = service.dataUpdate(vo);
			//E. 삭제된 파일을 제거
			if(vo.getDelFile()!=null && vo.getDelFile().size()>0) {
				for(String delFileName :vo.getDelFile()) {
					File f = new File(path, delFileName);
					f.delete();
				}
			}
			
			//F. 뷰페이지로 이동 -> 글내용보기
			mav.setViewName("redirect:view/"+vo.getRecipeno());
			
		}catch(Exception e) {
			e.printStackTrace();
			
			//가. rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//나. 새로업로드한 파일지우기
			for(RecipefileVO dfVO:newFileList) {
				File f = new File(path, dfVO.getFilename());
				f.delete();
			}
			//다. 이전페이지로 이동(자료실 수정페이지)
			mav.setViewName("data/dataResult");
		}
		return mav;
	}
	//자료실 삭제
	@GetMapping("/del/{no}")
	@Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
	public ModelAndView dataDelete(@PathVariable("no") int no, HttpSession session) {
		String path = session.getServletContext().getRealPath("/uploadfile");
		ModelAndView mav = new ModelAndView();
		String userid = (String)session.getAttribute("logId");
		try {
			//첨부된 파일명을 먼저 선택-> 파일삭제를 위하여
			List<RecipefileVO> fileList = service.getDataFile(no);
			
			int result = service.dataRecordDelete(no, userid); // 원글, 첨부파일명 레코드가 삭제된다. - Cascade떄문에
			//삭제 되었을때
			for(RecipefileVO vo : fileList) {
				File f = new File(path, vo.getFilename());
				f.delete();
			}
			
			mav.setViewName("redirect:/data/list");
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			//삭제못했을때
			mav.setViewName("redirect:/data/view/"+no);
		}
		return mav;
	}
	
	//파일업로드 구현
	public List<RecipefileVO> fileUpload(String path, List<MultipartFile> filesList) {
		// 업로드 한 파일목록을 보관 DatafileVO -> List
		List<RecipefileVO> uploadFileList = new ArrayList<RecipefileVO>();
		
		if(filesList != null){//업로드 파일이 있을때
			for(int i=0; i<filesList.size(); i++) {// 첨부된 파일 만큼 반복수행
				MultipartFile mf = filesList.get(i);
				
				String orgFilename = mf.getOriginalFilename();//클라이언트가 선택한 원래 파일명을 구한다.
				
				if(orgFilename!=null && !orgFilename.equals("")){ // if2
					//이미 서버에 같은 파일이 있으면 rename을 수행한다.  car.jpg -> car(1).jpg -> car(2).jpg -> car(3).jpg
					
					File f = new File(path, orgFilename);
					
					if(f.exists()) {// 파일이 있으면 true, 파일이 없으면 false if3
						// rename -> 파일명 찾기
						for(int renameNum = 1; ;renameNum++) {
							//확장자와 파일명분리
							int point = orgFilename.indexOf(".");//점위치구하기
							String filenameNoExt = orgFilename.substring(0, point);//파일명(확장자빼고) car
							String ext = orgFilename.substring(point+1);//확장자 gif
							
							//새로운 파일명 만들기
							String newFilename = filenameNoExt+"("+renameNum+")."+ext; // car(1).jpg
							f = new File(path, newFilename);
							if(!f.exists()) {//새로운 파일명이 서버에 있는지 확인 if4
								orgFilename = newFilename; // 새로 만든 파일이 서버에 없으면 반복중단
								break;
							}//if 4
						}// for2
						
					}//if3
					//업로드
					try {
						mf.transferTo(f);//서버에 실제 업로드 되는 시점.
					}catch(Exception e) {}
					
					//업로드 
					RecipefileVO fVO = new RecipefileVO();
					fVO.setFilename(orgFilename);
					uploadFileList.add(fVO);
				}//if2
			}//for1
			
		}//if1
		return uploadFileList;
	}
}