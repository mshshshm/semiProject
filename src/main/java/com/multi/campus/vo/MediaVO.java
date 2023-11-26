package com.multi.campus.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter //모든 변수에 getter
//@Setter	//모든 변수에 setter

//@ToString //toString() 오버라이딩
@NoArgsConstructor //매개변수 없는 생성자
@AllArgsConstructor	//매개변수 전체가 있는 생성자

@Data // Getter, Setter, toString, equals를 한번에 생성해줌
public class MediaVO {
	private int boardno;
	private String subject;
	private String content;
	private String usernickname;
	private String userid;
	private int hit;
	private String writedate;
	private String ip;
	private String category;
	//getter(), setter(), toString()
	
}
