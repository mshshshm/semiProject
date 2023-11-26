package com.multi.campus.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter //��� ������ getter
//@Setter	//��� ������ setter

//@ToString //toString() �������̵�
@NoArgsConstructor //�Ű����� ���� ������
@AllArgsConstructor	//�Ű����� ��ü�� �ִ� ������

@Data // Getter, Setter, toString, equals�� �ѹ��� ��������
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
