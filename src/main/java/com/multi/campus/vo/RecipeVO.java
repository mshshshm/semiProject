package com.multi.campus.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor
@AllArgsConstructor
public class RecipeVO {
	private int recipeno;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	
	//삭제된 파일정보, 배열, 컬렉션(List)
	private List<String> delFile;
}
