package com.multi.campus;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.BoardService;
import com.multi.campus.service.QnaService;
import com.multi.campus.service.RecipeService;
import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.RecipefileVO;
import com.multi.campus.vo.TipVO;
import com.multi.campus.vo.newsVO;

@Controller
public class HomeController {
	@Autowired
	QnaService service;
	@Autowired
	BoardService serviceB;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, PagingVO pVO) {
		//嫄닿컯�떇�떒
		String url = "https://news.google.com/search?q=%EA%B1%B4%EA%B0%95%EC%8B%9D%EB%8B%A8&hl=ko&gl=KR&ceid=KR%3Ako";
		List<newsVO> tlist = NewsCrawling.newsTitleList(url);
		List<newsVO> clist = NewsCrawling.newsCList(url);
		List<newsVO> dlist = NewsCrawling.newsDList(url);
		
		for(newsVO f: tlist) {
			f.setNewsUrl("https://news.google.com"+f.getNewsUrl().substring(1));
		}
		model.addAttribute("main", tlist.get(0));//main (title, url)
		model.addAttribute("sub", tlist.subList(1, 5) );//sub (title, url)
		model.addAttribute("com", clist.get(0));//company
		model.addAttribute("date", dlist.get(0));//date
		
		//�떇�옱猷�
		String url2 = "https://news.google.com/search?q=%EC%8B%9D%EC%9E%AC%EB%A3%8C&hl=ko&gl=KR&ceid=KR%3Ako";
		List<newsVO> tlist2 = NewsCrawling.newsTitleList(url2);
		List<newsVO> clist2 = NewsCrawling.newsCList(url2);
		List<newsVO> dlist2 = NewsCrawling.newsDList(url2);
		
		for(newsVO z: tlist2) {
			z.setNewsUrl("https://news.google.com"+z.getNewsUrl().substring(1));
		}
		model.addAttribute("main2", tlist2.get(0));
		model.addAttribute("sub2", tlist2.subList(1, 5) );
		model.addAttribute("com2", clist2.get(0));
		model.addAttribute("date2", dlist2.get(0));
		
		//�떎�씠�뼱�듃
		String url3 = "https://news.google.com/search?q=%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8&hl=ko&gl=KR&ceid=KR%3Ako";
		List<newsVO> tlist3 = NewsCrawling.newsTitleList(url3);
		List<newsVO> clist3 = NewsCrawling.newsCList(url3);
		List<newsVO> dlist3 = NewsCrawling.newsDList(url3);
		
		for(newsVO y: tlist3) {
			y.setNewsUrl("https://news.google.com"+y.getNewsUrl().substring(1));
		}
		model.addAttribute("main3", tlist3.get(0));
		model.addAttribute("sub3", tlist3.subList(1, 5) );
		model.addAttribute("com3", clist3.get(0));
		model.addAttribute("date3", dlist3.get(0));
		
		//�떎�씠�뼱�듃
		String url4 = "https://news.google.com/search?q=%EC%A0%9C%EC%B2%A0%EC%9D%8C%EC%8B%9D&hl=ko&gl=KR&ceid=KR%3Ako";
		List<newsVO> tlist4 = NewsCrawling.newsTitleList(url4);
		List<newsVO> clist4 = NewsCrawling.newsCList(url4);
		List<newsVO> dlist4 = NewsCrawling.newsDList(url4);
		
		for(newsVO x: tlist4) {
			x.setNewsUrl("https://news.google.com"+x.getNewsUrl().substring(1));
		}
		model.addAttribute("main4", tlist4.get(0));
		model.addAttribute("sub4", tlist4.subList(1, 5) );
		model.addAttribute("com4", clist4.get(0));
		model.addAttribute("date4", dlist4.get(0));
		
//		List<QnaVO> qnalist = service.qnaPageList(pVO);
//		model.addAttribute("pVO", pVO);
//		model.addAttribute("qnalist", qnalist.subList(1, 5));
//		
//		List<BoardVO> listB = serviceB.boardPageList(pVO);
//		model.addAttribute("pVOB", pVO);
//		model.addAttribute("listB", listB.subList(1, 3));
//		
		
		return "home";
		
	}
	
}
