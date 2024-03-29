package com.test.gpu.controller;


import java.util.Iterator;
import java.util.List;

import com.test.server.entity.Serverlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.test.gpu.entity.Gpu;
import com.test.gpu.mapper.GpuMapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chaowu
 * @since 2021-10-12
 */
@Controller
@RequestMapping(value = "/gpu")
public class GpuController {
	@Autowired
	GpuMapper mapper ;

	@GetMapping("")
	public String helloWorld(Model model) throws Exception {
		List<Gpu> list = mapper.queryAll();
		//int count = mapper.queryCount();
		model.addAttribute("serverlist", list);
		model.addAttribute("count", list.size()
		);
		return "/index";
	}

	@GetMapping("/index")
	public String index(Model model) throws Exception {
		List<Gpu> list = mapper.queryAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Gpu serverlist = (Gpu) iterator.next();
			System.out.println(serverlist.toString());
		}
		model.addAttribute("serverlist", list);
		return "/index";
	}


}
