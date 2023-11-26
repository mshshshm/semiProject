package com.multi.campus.vo;

import lombok.Data;

@Data
public class BoardReplyVO {
	private int replyno;
	private int boardno;
	private String content;
	private String userid;
	private String writedate;
}
