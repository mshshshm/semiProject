package com.multi.campus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.multi.campus.service.ScrapService;

@Controller
public class ScrapController {
	@Autowired
	ScrapService service;
	
}
