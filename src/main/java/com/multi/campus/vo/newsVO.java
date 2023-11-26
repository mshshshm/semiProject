package com.multi.campus.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class newsVO {
	private String newsTitle;
	private String newsCompany;
	private String newsUrl;
	private String newsDate;
}
