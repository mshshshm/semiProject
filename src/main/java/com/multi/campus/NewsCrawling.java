package com.multi.campus;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.multi.campus.vo.newsVO;

public class NewsCrawling {
	
	public static List<newsVO> newsTitleList(String url) {
		
		List<newsVO> newsList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements nt = doc.getElementsByAttributeValue("class", "DY5T1d RZIKme");
			
			for(Element e : nt) {
				newsVO news = newsVO.builder()
						.newsTitle(e.text())
						.newsUrl(e.attr("href"))
						.build();
				newsList.add(news);
						
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
	
	public static List<newsVO> newsCList(String url) {
		List<newsVO> newsList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements nc = doc.getElementsByAttributeValue("class", "wEwyrc");
			
			for(Element e : nc) {
				newsVO news = newsVO.builder()
						.newsCompany(e.text())
						.build();
				newsList.add(news);
						
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
	
	public static List<newsVO> newsDList(String url) {
		List<newsVO> newsList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements nd = doc.getElementsByAttributeValue("class", "WW6dff uQIVzc Sksgp slhocf");
			
			for(Element e : nd) {
				newsVO news = newsVO.builder()
						.newsDate(e.text())
						.build();
				newsList.add(news);
						
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
}
